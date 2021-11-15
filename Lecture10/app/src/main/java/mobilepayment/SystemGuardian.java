package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class SystemGuardian extends AbstractBehavior<SystemGuardian.KickOff> {
    
    /* --- Messages ------------------------------------- */
    public static final class KickOff{ }


    /* --- State ---------------------------------------- */
    // To be Implemented
    

    /* --- Constructor ---------------------------------- */
    private SystemGuardian(ActorContext<KickOff> context) {
        super(context);
    }

    /* --- Actor initial behavior ----------------------- */
    public static Behavior<KickOff> create()
    {
        return Behaviors.setup(SystemGuardian::new);
    }

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<KickOff> createReceive()
    {
        return newReceiveBuilder().onMessage(KickOff.class, this::onKickoff).build();
    }

    /* --- Handlers ------------------------------------- */
    public Behavior<KickOff> onKickoff(KickOff msg)
    {
        final ActorRef<MobileApp.Message> mobile1 = getContext().spawn(MobileApp.create(), "mobile1");
        final ActorRef<MobileApp.Message> mobile2 = getContext().spawn(MobileApp.create(), "mobile2");
        final ActorRef<Account.TransactionMessage> account1 = getContext().spawn(Account.create(100), "account1");
        final ActorRef<Account.TransactionMessage> account2 = getContext().spawn(Account.create(100), "account2");
        final ActorRef<Bank.MobileMessage> Bank1 = getContext().spawn(Bank.create(), "Bank1");
        final ActorRef<Bank.MobileMessage> Bank2 = getContext().spawn(Bank.create(), "Bank2");
        mobile1.tell(new MobileApp.Message(10, Bank1, account1, account2));
        mobile2.tell(new MobileApp.Message(10, Bank2, account2, account1));
        return this;
    }

}
