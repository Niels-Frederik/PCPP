package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import java.util.ArrayList;
import java.util.List;

public class Bank extends AbstractBehavior<Bank.MobileMessage> {

    /* --- Messages ------------------------------------- */
    public static final class MobileMessage{
        public ActorRef<Account.TransactionMessage> Sender;
        public ActorRef<Account.TransactionMessage> Receiver;
        public int amount;

        public MobileMessage(int amount, ActorRef<Account.TransactionMessage> sender, ActorRef<Account.TransactionMessage> receiver)
        {
            this.amount = amount;
            this.Sender = sender;
            this.Receiver = receiver;
        }
    }

    /* --- State ---------------------------------------- */
    //private List<MobileMessage> inbox;

    /* --- Constructor ---------------------------------- */
    private Bank(ActorContext<MobileMessage> context)
    {
        super(context);
        //this.inbox = new ArrayList<>();
    }

    /* --- Actor initial behavior ----------------------- */
    public static Behavior<MobileMessage> create()
    {
        return Behaviors.setup(context -> new Bank(context));
    }

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<MobileMessage> createReceive()
    {
        return newReceiveBuilder().onMessage(MobileMessage.class, this::onMessage).build();
    }

    /* --- Handlers ------------------------------------- */
    public Behavior<MobileMessage> onMessage(MobileMessage msg)
    {
        msg.Sender.tell(new Account.TransactionMessage(-msg.amount));
        msg.Receiver.tell(new Account.TransactionMessage(msg.amount));
        return this;
    }
}
