package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import akka.japi.pf.ReceiveBuilder;

public class Account extends AbstractBehavior<Account.TransactionMessage> {

    /* --- Messages ------------------------------------- */
    public static final class TransactionMessage {
        int amount;

        public TransactionMessage(int amount) {
            this.amount = amount;
        }
    }

    /* --- State ---------------------------------------- */
    private int balance;
    //private final List<Integer> Messages;

    /* --- Constructor ---------------------------------- */
    private Account(ActorContext<TransactionMessage> context, int initialBalance)
    {
        super(context);
        this.balance = initialBalance;
    }

    /* --- Actor initial behavior ----------------------- */
    public static Behavior<TransactionMessage> create(int balance)
    {
        return Behaviors.setup(context -> new Account(context, balance));
    }

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<TransactionMessage> createReceive()
    {
        return newReceiveBuilder().onMessage(TransactionMessage.class, this::onMessage).build();
    }

    /* --- Handlers ------------------------------------- */
    public Behavior<TransactionMessage> onMessage(TransactionMessage msg)
    {
        balance+=msg.amount;

        getContext().getLog().info("{} transacted {} : new balance {}",
                getContext().getSelf().path().name(),
                msg.amount, balance);
        return this;
    }
}
