package es.um.sisdist.facedetect;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openimaj.image.FImage;
import org.openimaj.image.Image;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.colour.Transforms;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import org.openimaj.video.Video;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.VideoDisplay.EndAction;
import org.openimaj.video.VideoDisplayListener;
import org.openimaj.video.VideoPositionListener;
import org.openimaj.video.xuggle.XuggleVideo;

/**
 * OpenIMAJ Hello world!
 *
 */
public class VideoFaces
{
    public static void main(String[] args) throws IOException
    {
        // VideoCapture vc = new VideoCapture( 320, 240 );
        // VideoDisplay<MBFImage> video = VideoDisplay.createVideoDisplay( vc );
        Video<MBFImage> video = new XuggleVideo(
                new File(args.length == 0
                        ? "videos/face-demographics-walking-and-pause.mp4"
                        : args[0]));
        VideoDisplay<MBFImage> vd = VideoDisplay.createOffscreenVideoDisplay(video);

        // El Thread de procesamiento de vídeo se termina al terminar el vídeo.
        vd.setEndAction(EndAction.CLOSE_AT_END);

        vd.addVideoListener(new VideoDisplayListener<MBFImage>() {
            // Número de imagen
            int imgn = 0;

            @Override
            public void beforeUpdate(MBFImage frame)
            {
                FaceDetector<DetectedFace, FImage> fd = new HaarCascadeDetector(40);
                List<DetectedFace> faces = fd.detectFaces(Transforms.calculateIntensity(frame));

                for (DetectedFace face : faces)
                {
                    frame.drawShape(face.getBounds(), RGBColour.RED);
                    try
                    {
                        // También permite enviar la imagen a un OutputStream
                        ImageUtilities.write(frame.extractROI(face.getBounds()),
                                new File(String.format("/tmp/img%05d.jpg", imgn++)));
                    } catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("!");
                }
            }

            @Override
            public void afterUpdate(VideoDisplay<MBFImage> display)
            {
            }
        });

        vd.addVideoPositionListener(new VideoPositionListener() {
            @Override
            public void videoAtStart(VideoDisplay<? extends Image<?, ?>> vd)
            {
            }

            @Override
            public void videoAtEnd(VideoDisplay<? extends Image<?, ?>> vd)
            {
                System.out.println("End of video");
            }
        });

        System.out.println("Fin.");
    }
}
