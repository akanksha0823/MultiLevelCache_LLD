package cache.model;

public class LevelCacheData {
    int readTime;
    int writeTime;

    public LevelCacheData(int readTime, int writeTime) {
        this.readTime = readTime;
        this.writeTime = writeTime;
    }

    public int getReadTime() {
        return readTime;
    }

    public int getWriteTime() {
        return writeTime;
    }
}
