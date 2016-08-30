#begin main scenario
scenario="Welcome";
default_font_size=24;
default_font="Arial";
default_background_color=0,0,0;
response_matching = simple_matching;
active_buttons=2;
#default_monitor_sounds=false;
begin;

	
picture { text{caption="Hit enter to begin.";}welcome; x=0;y=0;} welcome_pic;

picture { text{caption=" ";}blank; x=0;y=0;} blank_pic;

picture {bitmap {filename="S:\\Projects\\CIRL\\Experiments\\2013\\13_04_Spanish_babybio\\Presentation\\Stimuli\\pics\\1.jpg";}A1; x=0;y=0;}pic1;

video {filename="S:\\Projects\\CIRL\\Experiments\\2013\\13_04_Spanish_babybio\\Presentation\\Stimuli\\video\\Nino1.avi";}vid;
	
sound{wavefile {filename="S:\\Projects\\CIRL\\Experiments\\2013\\13_04_Spanish_babybio\\Presentation\\Stimuli\\audio\\Nino1.wav";}snd1;}snd;

trial {
	trial_type=first_response;
	trial_duration=forever;
	picture welcome_pic;
}welcome_trial;

trial {
	trial_type=first_response;
	trial_duration=forever;
	picture blank_pic;
}blank_trial;

trial {
	trial_type = fixed;	
	trial_duration = 3000;

	stimulus_event{
	
	picture pic1;
	}event3;
}pic_trial;

#trial {
#	stimulus_event{
#	sound snd;time=0;}vidsnd_event;
		
#stimulus_event{
#	video vid;time=0;}vid_event;
#}vid_trial;

trial {
	trial_duration = stimuli_length;
	trial_type = fixed;
	stimulus_event{

	video vid;
	}event2;
}trial2;


	
begin_pcl;

welcome_trial.present ();
welcome.set_caption(" ");
welcome.redraw();
loop
	int j=81;
	
	int picnum=17; #the number used in counting up for filename
	int funpic=81; #says how many trials between each presentation
	string picfilename=" "; #the new filename of the picture
	
	until
	j > 100
	begin
		vid.set_use_audio(false);
		string vidName="S:\\Projects\\CIRL\\Experiments\\2013\\13_04_Spanish_babybio\\Presentation\\Stimuli\\video\\Nino"+string(j)+".avi";
		term.print("\nVideonumber:"+vidName);

		vid.set_filename(vidName);

		string sndName="S:\\Projects\\CIRL\\Experiments\\2013\\13_04_Spanish_babybio\\Presentation\\Stimuli\\audio\\Nino"+string(j)+".wav";
		snd1.set_filename(sndName);
		vid.prepare();
		snd1.load();
		snd.present();		
		trial2.present();
		blank_trial.present();
		
		if funpic==j then
		picfilename= "S:\\Projects\\CIRL\\Experiments\\2013\\13_04_Spanish_babybio\\Presentation\\Stimuli\\pics\\"+string(picnum)+".jpg";
		A1.set_filename(picfilename);		
		A1.load();
		pic_trial.present();	
		A1.set_filename("S:\\Projects\\CIRL\\Experiments\\2013\\13_04_Spanish_babybio\\Presentation\\Stimuli\\pics\\blank.jpg");
		A1.load();
		funpic=funpic+5;
		picnum=picnum+1;
		end;

term.print("\nVideonumber:"+vidName);
term.print("\nsound_number:"+sndName);
	j=j+1;
end;

welcome.set_caption("Trial complete");
welcome.redraw();
welcome_trial.present();