package hello.jdbc.repository;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;

public class Sdd {
    public static void main(String[] args) {
        String imagePath = "C:\\Users\\josey\\Desktop\\1_image\\1212.jpg";
        String text = "무호흡펀치맞고사망";
        int x = 100; // 텍스트 시작 위치 x좌표
        int y = 200; // 텍스트 시작 위치 y좌표
        double rotation = Math.toRadians(-15.5); // 텍스트 기울임 각도

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            Graphics2D graphics = image.createGraphics();

            // 글꼴 및 색상 설정
            Font font = new Font("HYBSRB",Font.PLAIN, 135);
            Color color = new Color(71,80,97);
            graphics.setFont(font);
            graphics.setColor(color);

            // 텍스트 크기 계산
            FontMetrics fontMetrics = graphics.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(text);

            // 텍스트 시작 위치 계산
//            int startX = x + (image.getWidth() - textWidth) / 2;
//            int startY = y;
            int startX = 1278;
            int startY = 490;

            // 이미지에 텍스트 그리기
            drawRotatedText(graphics, text, startX, startY, rotation);

            String fileName = UUID.randomUUID().toString();

            // 이미지 저장
            String outputImagePath = "C:\\Users\\josey\\Desktop\\1_image\\save\\"+ fileName +".png";
            ImageIO.write(image, "png", new File(outputImagePath));

            System.out.println("이미지에 텍스트가 성공적으로 추가되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void drawRotatedText(Graphics2D graphics, String text, int x, int y, double rotation) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(rotation, x, y);
        graphics.setTransform(transform);

        for (int i = 0; i < text.length(); i++) {
            String character = String.valueOf(text.charAt(i));
            graphics.drawString(character, x, y + (i * graphics.getFontMetrics().getHeight()));
        }
    }
}
