package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

// Hint: You may generate random numbers using Random::ints
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class MobileApp extends AbstractBehavior<MobileApp.Message> {

    /* --- Messages ------------------------------------- */
    public static final class Message{
        public int amount;
        public ActorRef<Bank.MobileMessage> UsingBank;
        public ActorRef<Account.TransactionMessage> From;
        public ActorRef<Account.TransactionMessage> To;

        public Message(int amount, ActorRef<Bank.MobileMessage> bank, ActorRef<Account.TransactionMessage> from, ActorRef<Account.TransactionMessage> to)
        {
            this.amount = amount;
            this.UsingBank = bank;
            this.From = from;
            this.To = to;
        }
    }

    /* --- State ---------------------------------------- */
    // To be Implemented
    

    /* --- Constructor ---------------------------------- */
    private MobileApp(ActorContext<Message> context)
    {
        super(context);
    }

    /* --- Actor initial behavior ----------------------- */
    public static Behavior<Message> create()
    {
        return Behaviors.setup(context -> new MobileApp(context));
    }

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<Message> createReceive()
    {
        return newReceiveBuilder().onMessage(Message.class, this::onMessage).build();
    }

    /* --- Handlers ------------------------------------- */
    public Behavior<Message> onMessage(Message msg)
    {
        //var stream = Random::ints;
        var stream = new Random().ints();
        stream.map(x -> x%100).limit(100).forEach(x -> {
            msg.UsingBank.tell(new Bank.MobileMessage(x, msg.From, msg.To));
        });

       // getContext().getLog().info("{} sending {} from {} to {}",
       //         getContext().getSelf().path().name(),
       //         msg.amount, msg.From.path().name(), msg.To.path().name());

        //msg.UsingBank.tell(new Bank.MobileMessage(msg.amount, msg.From, msg.To));
        return this;
    }
}
