package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

// Hint: You may generate random numbers using Random::ints
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class MobileApp extends AbstractBehavior<Bank.MobileMessage> {

    /* --- Messages ------------------------------------- */
    // To be Implemented
    

    /* --- State ---------------------------------------- */
    // To be Implemented
    

    /* --- Constructor ---------------------------------- */
    private MobileApp(ActorContext<Bank.MobileMessage> context)
    {
        super(context);
    }

    /* --- Actor initial behavior ----------------------- */
    public static Behavior<Bank.MobileMessage> create()
    {
        return Behaviors.setup(context -> new MobileApp(context));
    }

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<Bank.MobileMessage> createReceive()
    {
        return newReceiveBuilder().onMessage(Bank.MobileMessage.class, this::onMessage).build();
    }

    /* --- Handlers ------------------------------------- */
    public Behavior<Bank.MobileMessage> onMessage(Bank.MobileMessage msg)
    {
        return this;
    }
}
