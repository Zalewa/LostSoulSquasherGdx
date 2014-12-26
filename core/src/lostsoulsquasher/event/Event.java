package lostsoulsquasher.event;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private List<EventHandler> handlers = new ArrayList<EventHandler>();

    public Event() {
    }

    public void add(EventHandler handler) {
        remove(handler);
        handlers.add(handler);
    }

    public void remove(EventHandler handler) {
        handlers.remove(handler);
    }

    public void fire(Object sender) {
        fire(sender, EventArgs.Zero);
    }

    public void fire(Object sender, EventArgs args) {
        for (EventHandler handler : handlers) {
            handler.run(sender, args);
        }
    }
}
