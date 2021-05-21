import java.awt.*;

public class ColorScheme {



//    public enum colorEnum{
//        lightMode(1),
//        normalMode(2),
//        darkMode(3);
//        colorEnum(int number){
//            switch (number) {
//                case 1 -> {
//                    this.primaryColor = new Color(249, 247, 247);
//                    this.secondaryColor = new Color(219, 226, 239);
//                    this.detailColor = new Color(63, 114, 175);
//                    this.firstBackgroundColor = primaryColor;
//                    this.secondBackgroundColor = secondaryColor;
//                    this.borderColor = detailColor.brighter();
//                    this.headerColor = null;
//                }
//                case 2 -> {
//                    this.primaryColor = new Color(67, 136, 204);
//                    this.secondaryColor = new Color(238, 238, 238);
//                    this.detailColor = Color.BLACK;
//                }
//                case 3 -> {
//                    this.primaryColor = new Color(34, 40, 49);
//                    this.secondaryColor = new Color(57, 62, 70);
//                    this.detailColor = new Color(238, 238, 238);
//                    this.firstBackgroundColor = secondaryColor;
//                    this.secondBackgroundColor = primaryColor;
//                    this.borderColor = primaryColor;
//                    this.headerColor = primaryColor;
//                }
//            }
//        }

        public static Color primaryColor = new Color(249, 247, 247);;
        public static Color secondaryColor = new Color(219, 226, 239);
        public static Color detailColor = new Color(63, 114, 175);
        public static Color firstBackgroundColor = primaryColor;
        public static Color secondBackgroundColor = secondaryColor;
        public static Color borderColor = detailColor.brighter();
        public static Color headerColor = null;

        public static Color getDetailColor() {
            return detailColor;
        }

        public static Color getPrimaryColor() {
            return primaryColor;
        }

        public static Color getSecondaryColor() {
            return secondaryColor;
        }

        public static Color getFirstBackgroundColor() {
            return firstBackgroundColor;
        }

        public static Color getSecondBackgroundColor() {
            return secondBackgroundColor;
        }

        public static Color getBorderColor() {
            return borderColor;
        }

        public static Color getHeaderColor() {
            return headerColor;
        }
    }


