package cache.model;

public class WriteResponse {
    Double timeTaken;

    public WriteResponse(Double timeTaken) {
        this.timeTaken = timeTaken;
    }

    public Double getTimeTaken() {
        return timeTaken;
    }

    @Override
    public String toString() {
        return "WriteResponse{" +
                "timeTaken=" + timeTaken +
                '}';
    }
}
