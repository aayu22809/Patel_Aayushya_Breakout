public class Score extends Node<Score> {
    private static final Color COLOR = Color.WHITE;
    private static final Font FONT = new Font("Arial", Font.BOLD, 20);

    private int score;

    public Score() {
        score = 0;
    }

    public void addPoints(int points) {
        if (points < 0) return;
        this.points += points;
    }

    public void subtractPoints(int points) {
        if (points > 0) return;
        this.points -= points;
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw the score
        g.setColor(COLOR);
        g.setFont(FONT);
        g.drawString("Score: " + score, 20, 30);
    }
     
}
