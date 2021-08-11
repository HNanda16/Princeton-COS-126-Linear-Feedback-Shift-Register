/* *****************************************************************************
 * Name: Hasit Nanda
 * NetID:
 * Precept:
 * Description: This program uses the LFSR class to implement a simple form of
 * encryption for digital pictures, using the random numbers generated using LFSR.
 * Applying the same function on the encrypted version, will decrypt
 * the image back to its original form, using the same password.
 *
 **************************************************************************** */

public class PhotoMagic {

    // returns a transformed copy of the specified picture, using the specified lfsr.
    public static Picture transform(Picture picture, LFSR lfsr) {
        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                int r = (picture.getRGB(i, j) >> 16) & 0xFF;
                int g = (picture.getRGB(i, j) >> 8) & 0xFF;
                int b = (picture.getRGB(i, j)) & 0xFF;
                r = r ^ lfsr.generate(8);
                g = g ^ lfsr.generate(8);
                b = b ^ lfsr.generate(8);
                int rgb = (r << 16) + (g << 8) + (b);
                picture.setRGB(i, j, rgb);

            }

        }

        return picture;
    }

    // takes the name of an image file and a description of an lfsr as command-line arguments
    // displays a copy of the image that is "encrypted" using the LFSR.
    public static void main(String[] args) {
        LFSR lfsr = new LFSR(args[1], Integer.parseInt(args[2]));
        Picture picture = new Picture(args[0]);
        transform(picture, lfsr).show();
    }
}
