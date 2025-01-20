

package arc.net;

abstract public class TcpIdleSender implements NetListener{
    boolean started;

    public void idle(Connection connection){
        if(!started){
            started = true;
            start();
        }
        Object object = next();
        if(object == null)
            connection.removeListener(this);
        else
            connection.sendTCP(object);
    }

    /**
     * Called once, before the first send. Subclasses can override this method
     * to send something so the receiving side expects subsequent objects.
     */
    protected void start(){
    }

    /**
     * Returns the next object to send, or null if no more objects will be sent.
     */
    abstract protected Object next();
}
