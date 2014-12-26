package lostsoulsquasher;

public class ExpirationActionBehavior implements Behavior {
    float expirationCountdown = 0.0f;

    public ExpirationActionBehavior(float expirationTime) {
        this.expirationCountdown = expirationTime;
    }

    @Override
    public void run(float elapsedTime, Entity entity) {
        expirationCountdown -= elapsedTime;
        if (expirationCountdown <= 0.0f) {
            entity.setExpired(true);
        }
    }
}
