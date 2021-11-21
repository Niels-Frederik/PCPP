package mathsserver;

// Hint: The imports below may give you hints for solving the exercise.
//       But feel free to change them.

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.ChildFailed;
import akka.actor.typed.Terminated;
import akka.actor.typed.javadsl.*;

import java.util.*;
import java.util.stream.IntStream;

import mathsserver.Task;
import mathsserver.Task.BinaryOperation;

public class Server extends AbstractBehavior<Server.ServerCommand> {
    /* --- Messages ------------------------------------- */
    public interface ServerCommand { }
    
    public static final class ComputeTasks implements ServerCommand {
	public final List<Task> tasks;
	public final ActorRef<Client.ClientCommand> client;

	public ComputeTasks(List<Task> tasks,
				 ActorRef<Client.ClientCommand> client) {
	    this.tasks  = tasks;
	    this.client = client;
	}
    }

    public static final class WorkDone implements ServerCommand {
	ActorRef<Worker.WorkerCommand> worker;

	public WorkDone(ActorRef<Worker.WorkerCommand> worker) {
	    this.worker = worker;
	}
    }
    
    /* --- State ---------------------------------------- */
    Queue<Map.Entry<Task,ActorRef<Client.ClientCommand>>> taskQueue;
    Queue<ActorRef<Worker.WorkerCommand>> idleWorkers;
    Random rand = new Random();
    int busyWorkers;
    int minWorkers;
    int maxWorkers;

    /* --- Constructor ---------------------------------- */
    private Server(ActorContext<ServerCommand> context,
		   int minWorkers, int maxWorkers) {
    	super(context);
        taskQueue = new LinkedList<>();
        this.minWorkers = minWorkers;
        this.maxWorkers = maxWorkers;
        idleWorkers = new LinkedList<>();
        //busyWorkers = new LinkedList<>();
        busyWorkers = 0;
        for(int i = 1; i < minWorkers+1; i++)
        {
            var worker = CreateWorker("worker_"+i);
            idleWorkers.add(worker);
        }

    }

    public ActorRef<Worker.WorkerCommand> CreateWorker(String name)
    {
        var worker = getContext().spawn(Worker.create(getContext().getSelf()), name);
        getContext().watch(worker);
        return worker;
    }

    /* --- Actor initial state -------------------------- */
    public static Behavior<ServerCommand> create(int minWorkers, int maxWorkers) {
    	return Behaviors.setup(context -> new Server(context, minWorkers, maxWorkers));
    }
    

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<ServerCommand> createReceive() {
    	return newReceiveBuilder()
    	    .onMessage(ComputeTasks.class, this::onComputeTasks)
    	    .onMessage(WorkDone.class, this::onWorkDone)
            .onSignal(ChildFailed.class, this::onChildFailed)
            .onSignal(Terminated.class, this::onTerminated)
    	    .build();
    }


    /* --- Handlers ------------------------------------- */
    public Behavior<ServerCommand> onComputeTasks(ComputeTasks msg) {
        for(var task : msg.tasks)
        {
            if (idleWorkers.size() > 0)
            {
                var worker = idleWorkers.poll();
                worker.tell(new Worker.ComputeTask(task, msg.client));
                busyWorkers++;
            }
            else if (busyWorkers < maxWorkers)
            {
                var worker= CreateWorker("worker_"+(busyWorkers+1));
                worker.tell(new Worker.ComputeTask(task, msg.client));
                busyWorkers++;
            }
            else taskQueue.add(Map.entry(task, msg.client));
        }
    	return this;
    }

    public Behavior<ServerCommand> onWorkDone(WorkDone msg) {
        if (taskQueue.size() > 0)
        {
            var task = taskQueue.poll();
            msg.worker.tell(new Worker.ComputeTask(task.getKey(), task.getValue()));
        }
        else
        {
            idleWorkers.add(msg.worker);
            busyWorkers--;
        }
	return this;
    }

    public Behavior<ServerCommand> onChildFailed(ChildFailed msg) {
        var workerName = "worker_" + rand.nextInt(Integer.MAX_VALUE - maxWorkers) + maxWorkers;
        getContext().getLog().info("Child failed, started new worker: " + workerName);
        idleWorkers.add(CreateWorker(workerName));
        busyWorkers--;
        return this;
    }

    public Behavior<ServerCommand> onTerminated(Terminated msg) {
        getContext().getLog().info("Worker stopped");
        busyWorkers--;
        return this;
    }
}
