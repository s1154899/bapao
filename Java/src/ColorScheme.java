import java.awt.*;

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
                    borderColor = primaryColor;
                    headerColor = primaryColor;
                }
            }
        }

        public Color primaryColor = new Color(249, 247, 247);;
        public Color secondaryColor = new Color(219, 226, 239);
        public Color detailColor = new Color(63, 114, 175);
        public Color firstBackgroundColor = primaryColor;
        public Color secondBackgroundColor = secondaryColor;
        public Color borderColor;
        public Color headerColor = null;

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
    }
}


