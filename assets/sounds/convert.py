#!/usr/bin/python3 
import subprocess

def convert_and_split(filename):
    command = ['ffmpeg', '-i', filename, '-f', 'segment', '-segment_time', '15', 'out%09d.wav']
    subprocess.run(command,stdout=subprocess.PIPE,stdin=subprocess.PIPE)

convert_and_split("./start-music.webm")
