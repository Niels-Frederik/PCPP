package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import java.util.ArrayList;
import java.util.List;

public class Bank extends AbstractBehavior<Bank.MobileMessage> {

    /* --- Messages ------------------------------------- */
    public static final class MobileMessage{
        public int amount;
        public ActorRef<Account> sender;
        public ActorRef<Account> receiver;

        public MobileMessage(int amount, ActorRef<Account> sender, ActorRef<Account> receiver)
        {
            this.amount = amount;
            this.sender = sender;
            this.receiver = receiver;
        }
    }

    /* --- State ---------------------------------------- */
    private List<MobileMessage> inbox;

    /* --- Constructor ---------------------------------- */
    private Bank(ActorContext<MobileMessage> context)
    {
        super(context);
        this.inbox = new ArrayList<>();
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
        msg.sender.tell(new Account.TransactionMessage(msg.amount));
        msg.receiver.tell(new Account.TransactionMessage(msg.amount));
        return this;
    }
}
