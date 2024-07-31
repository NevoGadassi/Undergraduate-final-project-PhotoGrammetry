import sys
import subprocess
import os
import urllib.request
import webbrowser
import cv2
from skimage.metrics import structural_similarity as ssim

def compare_frames(frame1, frame2):

    """

    Compare two frames using SSIM



    Args:

        frame1 : First frame.

        frame2 : Second frame.



    Returns:

        float: SSIM value indicating similarity between frames.

    """

    # Convert frames to grayscale

    gray1 = cv2.cvtColor(frame1, cv2.COLOR_BGR2GRAY)

    gray2 = cv2.cvtColor(frame2, cv2.COLOR_BGR2GRAY)



    # Compute SSIM

    similarity = ssim(gray1, gray2)





    return similarity



def extract_frames(video_path, output_folder, threshold, interval):

        """

        Extract frames from a video and save them as images if similarity is not greater than threshold.



        Args:

            video_path (str): Path to the input video file.

            output_folder (str): Path to the output folder where images will be saved.

            threshold (float): Similarity threshold between frames. Frames with similarity that not greater than this value will be kept.

            interval (int): Interval between frame extraction.

        """

        # Open the video file

        video_capture = cv2.VideoCapture(video_path)

        count = 0

        # Create output folder if it doesn't exist

        import os

        if not os.path.exists(output_folder):

            os.makedirs(output_folder)



        saved_frames = []

        frame_count = 0
        while True:

            # Read a frame from the video

            success, frame = video_capture.read()

            # Break if no frame is retrieved

            if not success:
                break

            if frame_count == 0:
                saved_frames.append(frame.copy())

                frame_filename = os.path.join(output_folder, f"frame_{frame_count}.jpg")

                cv2.imwrite(frame_filename, frame)

                frame_count += 1



            should_save_frame = True

            if count % interval == 0:

            # Compare current frame with saved frames

                for saved_frame in saved_frames:
                    similarity = compare_frames(saved_frame, frame)
                    if similarity >= threshold:

                        should_save_frame = False

                        break



            # Save frame to collection if necessary

            if should_save_frame and count % interval == 0:

                saved_frames.append(frame.copy())
                frame_filename = os.path.join(output_folder, f"frame_{frame_count}.jpg")
                cv2.imwrite(frame_filename, frame)
                if success:
                    print(f"Frame {frame_count} saved successfully as {frame_filename}")
                else:
                    print(f"Failed to save frame {frame_count} as {frame_filename}")

                frame_count += 1

            count+=1

        # Release the video capture object

        video_capture.release()

        print("the all frame is :", count/interval)






def mergecloud(file1, file2):
    # Construct the full file paths based on the current working directory
    file1_path = os.path.join(os.getcwd(), file1)
    file2_path = os.path.join(os.getcwd(), file2)

    # Construct the command using the file paths
    command = fr'"C:\Users\נבו\CloudCompare_v2.13.1_bin_x64\CloudCompare" -O {file1_path} -O {file2_path} -ICP -MERGE_CLOUDS -SAVE_CLOUDS FILE "cloudmerge.bin" -O "cloudmerge.bin"'

    # Execute the command using subprocess
    process = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    stdout, stderr = process.communicate()
    # Check for any errors
    if stderr:
        print(f'Error executing command: {stderr.decode("utf-8")}')
    else:
        print(f'Command executed successfully:\n{stdout.decode("utf-8")}')

def print_help():
    """
    Print help message.
    """
    print()
    print("Final Project  photogrammetry [options]")
    print("Options:")
    print("-t, \t\tDisplay tutorial")
    print("-func, \t\tDisplay all the functions and explanation")
    print("-h, \t\tDisplay this help message")
    print()

def print_tutorial():
    """
    Print photogrammetry tutorial.
    """
    print()
    print("Photogrammetry Tutorial:")
    print("Welcome to our final project")
    print("Nevo Gadassi — : 325545887")
    print("Yuval Musseri — : 213358039")
    print("Ron Yakobovich — : 212767214")
    print("In this project, you will find our research work in the field of photogrammetry.")
    print("Its production is the merging of clouds and cuts of images from drone videos.")
    print("Have fun!")    # Add your tutorial steps here


def example_function():
    """
    Example function to demonstrate the use of -func option.
    """
    print()

    print("functions list:")

    print("create <folder_path>\tCreate photogrammetry from images in the specified folder")
    print("cloudview <folder_path>\tOpen window to chose and view points cloud")
    print("research \t To open our final Research project")
    print("mergecloud \t To merge between 2 cloud files")
    print("examplesvideo \t Url to all our videos in the project")
    print("framesfromvideo \t cut a video to important frames")
    print()
def create_photogrammetry(folder_path):
    """
    Create photogrammetry from images in the specified folder.

    :param folder_path: Path to the folder containing images for photogrammetry.
    """
    try:
        # Run the Java command to perform photogrammetry
        java_command = [
            'java', '-jar', 'applications/applications.jar', 'SceneReconstruction',
            '--WindowSize', '1920:1080', '--AllScenes', '--ShowCloud', '--TryHarder', '--Verbose',
            '-i', folder_path, '-o', 'output'
        ]

        # Start a new command prompt and execute the Java command

        cmd_path = r'C:\Windows\System32\cmd.exe'
        cmd_command = [cmd_path, '/k', 'cd', 'boofcv', '&&'] + java_command
        proc = subprocess.Popen(cmd_command, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        stdout, stderr = proc.communicate()
        print("Standard Output:", stdout.decode())
        print("Standard Error:", stderr.decode())
    except Exception as e:
        print("Error:", e)

def main():
    """
    Main function to parse command-line arguments and execute appropriate actions.
    """
    ascii_art = """ 
      ____  _           _                                                 _              
     |  _ \\| |__   ___ | |_ ___   __ _ _ __ __ _ _ __ ___  _ __ ___   ___| |_ _ __ _   _ 
     | |_) | '_ \\ / _ \\| __/ _ \\ / _` | '__/ _` | '_ ` _ \\| '_ ` _ \\ / _ \\ __| '__| | | |
     |  __/| | | | (_) | || (_) | (_| | | | (_| | | | | | | | | | | |  __/ |_| |  | |_| |
     |_|   |_| |_|\\___/ \\__\\___/ \\__, |_|  \\__,_|_| |_| |_|_| |_| |_|\\___|\\__|_|   \\__, |
                                 |___/                                             |___/ 
                                 """
    print(ascii_art)

    while True:

        # Open a new command prompt window

        command = input("Enter a command (type 'exit' to quit): ").strip().lower()
        if command == 'exit':
            print("Exiting...")
            break
        elif command in ['-h', '--help']:
            print_help()
        elif command in ['-t', '--tutorial']:
            print_tutorial()
        elif command == 'create':
            folder_path = input("Enter the folder path: ").strip()
            create_photogrammetry(folder_path)
        elif command == '-func':
            example_function()
        elif command == 'mergecloud':
            cloud1_path = input("Enter the first cloud path: ").strip()
            cloud2_path = input("Enter the second cloud path: ").strip()
            mergecloud(cloud1_path, cloud2_path)

        elif command == 'framesfromvideo':
            video_path = input("Enter the video  path: ").strip()
            output_path = r"C:\Users\Public\Frames"
            similarity_threshold = 0.5
            interval = 60
            extract_frames(video_path, output_path, similarity_threshold, interval)
        elif command == 'examplesvideo':
            url = "https://drive.google.com/drive/folders/1lVLObtb14JL5DzcC0LIdkItTlRLaqMzJ"
            webbrowser.open(url)
        elif command == 'cloudview':
            command = fr'"C:\Users\נבו\CloudCompare_v2.13.1_bin_x64\CloudCompare" "'
            process = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
            stdout, stderr = process.communicate()
        elif command == 'research':
            research_pdf_path = r"C:\Users\נבו\Photogrammetry.pdf"
            try:
                subprocess.run(['cmd', '/c', 'start', research_pdf_path], check=True)
            except subprocess.CalledProcessError as e:
                print("Error:", e)
        else:
            print("Error: Invalid command. Use '-h' or '--help' for usage information.")
    print()


if __name__ == "__main__":
    main()
