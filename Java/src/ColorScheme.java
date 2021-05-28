import java.awt.*;
import java.io.IOException;

public class ColorScheme {



    public enum colorEnum{
        lightMode(1),
        normalMode(2),
        darkMode(3);
        colorEnum(int number){
            switch (number) {
                case 1 -> {
                    this.primaryColor = new Color(249, 247, 247);
                    this.secondaryColor = new Color(219, 226, 239);
                    this.detailColor = new Color(63, 114, 175);
                    this.firstBackgroundColor = primaryColor;
                    this.secondBackgroundColor = secondaryColor;
                    this.borderColor = detailColor.brighter();
                    this.headerColor = null;
                    try {
                        usedFont = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Assets/Comfort.ttf"));
                    } catch (IOException |FontFormatException e) {
                        //Handle exception
                    }
                }
                case 2 -> {
                    this.primaryColor = new Color(67, 136, 204);
                    this.secondaryColor = new Color(238, 238, 238);
                    this.detailColor = Color.BLACK;
                }
                case 3 -> {
                    this.primaryColor = new Color(34, 40, 49);
                    this.secondaryColor = new Color(57, 62, 70);
                    this.detailColor = new Color(238, 238, 238);
                    this.firstBackgroundColor = secondaryColor;
                    secondBackgroundColor = primaryColor;
                    borderColor = detailColor;
                    headerColor = primaryColor;
                    try {
                        usedFont = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Assets/Comfort.ttf"));
                    } catch (IOException |FontFormatException e) {
                        //Handle exception
                    }
                }
                default -> {
                    this.primaryColor = new Color(249, 247, 247);
                    this.secondaryColor = new Color(219, 226, 239);
                    this.detailColor = new Color(63, 114, 175);
                    this.firstBackgroundColor = new Color(249, 247, 247);
                    this.secondBackgroundColor = new Color(219, 226, 239);
                    this.borderColor = new Color(63, 114, 175).brighter();
                    this.headerColor = null;
                    try {
                        usedFont = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Assets/Comfort.ttf"));
                    } catch (IOException |FontFormatException e) {
                        //Handle exception
                    }
                }
            }
        }

        public final Color primaryColor;
        public final Color secondaryColor;
        public final Color detailColor;
        public Color firstBackgroundColor;
        public Color secondBackgroundColor;
        public Color borderColor;
        public Color headerColor;
        public Font usedFont;

        public Color getDetailColor() {
            return detailColor;
        }

        public Color getPrimaryColor() {
            return primaryColor;
        }

        public Color getSecondaryColor() {
            return secondaryColor;
        }

        public Color getFirstBackgroundColor() {
            return firstBackgroundColor;
        }

        public Color getSecondBackgroundColor() {
            return secondBackgroundColor;
        }

        public Color getBorderColor() {
            return borderColor;
        }

        public Color getHeaderColor() {
            return headerColor;
        }

        public Font getUsedFont() {
            return usedFont;
        }
    }
}


