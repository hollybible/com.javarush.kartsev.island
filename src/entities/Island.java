package entities;


public class Island {
    private final Location[][] locations;
    private final int width;
    private final int height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        locations = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                locations[i][j] = new Location();
            }
        }
    }

    public Location getLocation(int x, int y) {
        return locations[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

