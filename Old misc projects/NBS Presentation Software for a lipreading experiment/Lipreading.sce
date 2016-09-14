scenario="Lipreading.sce";
default_font_size =  24;
default_font = "Arial";
default_background_color = 0,0,0;
active_buttons=3;
#button_codes=1;
default_monitor_sounds=false;

$snrtemp=0;

begin;
sound { wavefile {filename="S:\\Projects\\CIRL\\Experiments\\2013\\13_05_lipreading\\Presentation\\stimuli\\noise_15ch_10dBoc\\MultiTalkerBabble_amplify20dB.wav"; } noise_R;attenuation=$snrtemp;loop_playback=true; } backNoise_R; 
sound { wavefile {filename="S:\\Projects\\CIRL\\Experiments\\2013\\13_05_lipreading\\Presentation\\stimuli\\blank.wav"; } noise_L;attenuation=$snrtemp;loop_playback=true; } backNoise_L; 

picture { text{caption="Please enter a value between -20 and 20 SNR\n\nPress 'Enter' to return\n";}SNRwrong; x=0;y=0;}SNRwrong_pic;
picture { text{caption="Please enter a valid numerical answer\n\nPress 'Enter' to return\n";}Numwrong; x=0;y=0;}Numwrong_pic;
picture { text{caption="Please enter a List number between 0 and 16\Press 'Enter' to return\n";}Listwrong; x=0;y=0;}Listwrong_pic;
picture { text{caption="SNR=0";font_size=25;}SNRdisp; x=-550;y=500;}SNRdisp_pic;
picture { text{caption="List=0";font_size=25;}Listdisp; x=-450;y=500;}Listdisp_pic;
picture { text{caption="Mode=0";font_size=25;}Modedisp; x=-450;y=500;}Modedisp_pic;


video {filename="S:\\Projects\\CIRL\\Experiments\\2013\\13_05_lipreading\\Presentation\\stimuli\\Azbio_1_1.avi";}vid;
sound {wavefile {filename="S:\\Projects\\CIRL\\Experiments\\2013\\13_05_lipreading\\Presentation\\stimuli\\blank.wav";}wav1; }snd; 

box { height = 200; width = 200; color = 255,255,255; } white_box; 
box { height = 190; width = 190; color = 255,255,255; } small_white_box; 
box { height = 200; width = 200; color = 0,0,0; } blank_box; 
box { height = 200; width = 200; color = 250,0,0; } red_box;


$top_color = "33,67,105";
#$bottom_color = "122,150,180";
$bottom_color = "0,0,0";
$bottom_continue_y = -364;
$bottom_line_y= -344;
$top_main_text_y = 250;
$mid_line_y = 100;
$top_halfway_y = 300;
$bottom_halfway_y = -150;
box{color = $top_color; height = 400; width = 1024;}top_box;
box{color = $bottom_color; height = 500; width = 1024;}bottom_box;
box{color = $top_color; height = 40; width = 1024;}continue_box;
box{color = 0,0,0; height = 2; width = 1024;}line;
text{caption = " ";}dummy;
text{caption = " "; background_color = $bottom_color;}bottomdummy;
text{caption = "Click a mouse button to continue. Press escape to quit or 'm' to return to the menu."; font_size = 14; background_color = $top_color;}continue1;

sound {wavefile {filename = "S:\\Projects\\CIRL\\Experiments\\2013\\13_05_lipreading\\Presentation\\stimuli\\woosh.wav";};}menu_sound;
box {height = 10 ; width = 10;}border_box;



trial {
trial_type = fixed;

	stimulus_event{
	picture {
	box bottom_box;
	x=0; y=0;
	text { caption = "Choose mode:"; }text1;
   x = 0; y = 300;
   text { caption = " "; } input_text;
   x = 0; y = 0;
	text SNRdisp;
	x = -650; y=570;
	text Listdisp;
	x = 0; y=570;
	text Modedisp;
	x = 650; y=570;
	#box white_box;
	#x=-200; y=0;
	#box white_box;
	#x=200; y=0;
	#box small_white_box;
	#x=-200; y=0;
	#box small_white_box;
	#x=200; y=0;
	#text {caption="Audio only";font_size=25;background_color=255,255,255;font_color=0,0,0;};
	#x=-200; y=0;
	#text {caption="Audio+Video";font_size=25;background_color=255,255,255;font_color=0,0,0;};
	#x=200; y=0;
	
	#box { height = 10; width = 10; color = 0,255,0; };
  # x = 0; y = 0;
	} pic1;
	code="";
	
	}front_event;
}front_trial;


trial{
	trial_type=specific_response;
	trial_duration=forever;
	terminator_button=2,3;
	monitor_sounds=true;
	stimulus_event{
	picture {
		text { caption = " "; font_size=35;} notify_text;
		x = 0; y = 0;
		text SNRdisp;
		x = -650; y=570;
		text Listdisp;
		x = 0; y=570;
		text Modedisp;
		x = 650; y=570;
	} notify_pic;
	code="";
	} notify_event;
}notify_trial;	
	
trial{
trial_type=fixed;
trial_duration=50;
monitor_sounds=false;
	stimulus_event{
	sound backNoise_R;
};
	stimulus_event{
	sound backNoise_L;
	time=10;
};
}backNoise_trial;


trial{
	trial_type=fixed;
	picture {
	box { height = 220; width = 500; color = 0,0,0; } black_box; 
	x=0; y=0;
	text SNRdisp;
	x = -650; y=570;
	text Listdisp;
	x = 0; y=570;
	text Modedisp;
	x = 650; y=570;	}black_screen;
	time=0;
	duration=2200;

	stimulus_event{
	sound snd;
	time=1;
	}sound_event;
	
}sound_trial;
	
trial{
	trial_type=fixed;	
	stimulus_event{
	sound snd;time=1;}vidsnd_event;
	stimulus_event{
	video vid;time=0;}vid_event;
}vid_trial;
	
trial{
	trial_type=fixed;
stimulus_event{
picture {
		text { caption = "Enter Response:"; font_size=35;} feedback_instruc;
		x = 0; y = 100;
		text { caption = " "; font_size=35;} feedback;
		x = 0; y = 0;
		text SNRdisp;
		x = -650; y=570;
		text Listdisp;
		x = 0; y=570;
		text Modedisp;
		x = 650; y=570;
	} feedback_pic;
	code="";
}feedback_event;
}feedback_trial;


array {
	LOOP $i 20; 
	text {caption = " "; background_color = 44, 91, 143 ;};
	ENDLOOP;
}menu_text;

text{caption = "+"; font_size = 24; trans_src_color = 0,0,0; font = "Courier";}cursor;
text{caption = " "; font_size = 36; background_color = $bottom_color;}titletext;

trial{
	picture{
	background_color = $bottom_color;
	text SNRdisp;
	x = -650; y=570;
	text Listdisp;
	x = 0; y=570;
	text Modedisp;
	x = 650; y=570;
}pic;
}menu_screen;

trial{
	trial_type = specific_response;
	terminator_button = 1;
   trial_duration = forever;
   stimulus_event{
		picture{
			box top_box;
			x = 0; y = $top_halfway_y; 
			box bottom_box;
			x = 0; y = $bottom_halfway_y; 
			box line;
			x = 0; y = $mid_line_y; 
			text dummy; 
			x = 0; y = $top_main_text_y; 
			box continue_box;
			x = 0; y = $bottom_continue_y; on_top = true;
			box line;
			x = 0; y = $bottom_line_y; on_top = true;
			text continue1; 
			x = 0; y = $bottom_continue_y; on_top = true;
			text dummy; 
			x = 0; y = -130;
		}basic_pic;
	}basic_event;
}basic_trial;

trial{
	trial_type = specific_response;
	terminator_button = 1;
   trial_duration = forever;
   picture{
		background_color = $top_color;
		text dummy;
		x = 0; y = 0;
		box line;
		x = 0; y = $bottom_line_y;
		text continue1; 
		x = 0; y = $bottom_continue_y;
	}fullscreen_pic;
}fullscreen_trial;


begin_pcl;
#term.print(date_time( "mmddyy_hhss" ));
int Prac = 0;
string PracN=" ";
int mode = 0;
int k=0;
string Prefix=" ";
system_keyboard.set_min_size( 1 );
system_keyboard.CASE_SHIFT;
int NumSent=15;
string VidPrefix= " ";
int main_menu_choice = 0;


int max_x = display_device.width() / 2;
int min_x = -max_x;
int max_y = display_device.height() / 2;
int min_y = -max_y;
int bottom_max_y = 100; #for restricting mouse to bottom half
int bottom_min_y = min_y + 40; #for restricting mouse to bottom half
mouse mse = response_manager.get_mouse(1);

###-------re-used subroutines
sub wait (int wait_time)
begin
	loop int time = clock.time()
	until clock.time() > time + wait_time
	begin
	end;
end;

sub bool resp (int button, int current_count)
begin
	if response_manager.total_response_count(button) > current_count then
		return true
	end;
	return false;
end;

###-------end re-used subroutines

###-------begin menu pcl---
string listtype=" ";
sub int current_button(array<int,2>& locations, int x, int y)
begin
	loop
		int i = 1
	until
		i > locations.count()
	begin
		if ( (x >= locations[i][1]) && (x <= locations[i][2]) &&
			  (y <= locations[i][3]) && (y >= locations[i][4]) ) then
			return i
		end;
		i = i + 1
	end;
	return 0
end;


sub int menu (array<string,1>& options, string title)
begin
	mse.set_min_max( 1, min_x, max_x );
	mse.set_min_max( 2, min_y, max_y );
	mse.set_restricted( 1, true );
	mse.set_restricted( 2, true );
	###adjustable parameters for menu
	int border_width = 5;
	int box_dist = 20;
	##
	array<int> locations[options.count()][4];
	int number = options.count();
	int max_width = 0;
	###set the text captions into the text array and find the max_width
	loop int i = 1 until i > number begin
		menu_text[i].set_height(0);
		menu_text[i].set_width(0);
		menu_text[i].set_caption(options[i]);
		menu_text[i].redraw();
		if int(menu_text[i].width()) > max_width then
			max_width = int(menu_text[i].width());
		end;
		i = i + 1;
	end;
	###make the proper number of button boxes and border boxes with the right dimensions and colors
	int text_height = int(menu_text[number].height()) + 30;
	int box_height = text_height + border_width * 2;
	int text_width = max_width + 30;
	int box_width = text_width + border_width * 2;
	border_box.set_width( box_width);
	border_box.set_height( box_height );
	border_box.set_color( 0, 0, 0);
	loop int i = 1 until i > number 
	begin
		menu_text[i].set_width( text_width );
		menu_text[i].set_height( text_height );
		menu_text[i].set_background_color( 44, 91, 143 );
		menu_text[i].redraw();
		i = i + 1;
	end;
	###make array of locations
	int in_col = 0;
	int last_left_edge = -box_width/2;
	int last_right_edge = box_width/2;
	int last_top_edge = box_height/2;
	int last_bottom_edge = -box_height/2;
	###special case of only one choice
	if number == 1 then 
		locations[number][1] = last_left_edge; 
		locations[number][2] = last_right_edge;
		locations[number][3] = last_top_edge;
		locations[number][4] = last_bottom_edge;
		else
		###decide how many buttons per column
		if mod(number, 2) == 1 then
			in_col = number - 1;
			else 
			in_col = number;
		end;
		###how many rows
		int rows = in_col / 2;
		###put all edge locations in location array
		loop int i = 1;
			int left_edge = -(box_width + box_dist);
			until i > 2 
			begin
				int top_edge = (box_height + box_dist)*rows/2;
				loop int j = 1 until j > rows 
				begin
					int index = ((i-1) * rows) + j;
					int right_edge = (left_edge + box_width);
					int bottom_edge = top_edge - box_height;
					locations[index][1] = left_edge;
					locations[index][2] = right_edge;
					locations[index][3] = top_edge;
					locations[index][4] = bottom_edge;
					top_edge = top_edge - (box_height + box_dist);
					j = j + 1;
				end;
			left_edge = (left_edge + box_width + 2*box_dist);
			i = i + 1;
		end;
		if mod (number,2) == 1 then
			last_top_edge = (locations[number - 1][4] - box_dist);
			last_bottom_edge = last_top_edge - box_height;
			locations[number][1] = last_left_edge; 
			locations[number][2] = last_right_edge;
			locations[number][3] = last_top_edge;
			locations[number][4] = last_bottom_edge;
		end;
	end;
	###add buttons to picture pic
	loop int i = 1 until i > options.count()
		begin
		pic.add_part(border_box, locations[i][1] + box_width/2, locations[i][3] - box_height/2);
		pic.add_part(menu_text[i], locations[i][1] + box_width/2, locations[i][3] - box_height/2);
		i = i + 1;
	end;
	##add List Mode info to pic
	Listdisp.redraw();
	SNRdisp.redraw();
	Modedisp.redraw();
	pic.add_part(SNRdisp,-650,570);
	pic.add_part(Listdisp,0,570);
	pic.add_part(Modedisp,650,570);

	###add title to picture pic
	titletext.set_caption(title);
	titletext.redraw();
	pic.add_part(titletext, 0, 300);
	###add cursor to picture pic
	pic.add_part(cursor, 0, 0);
	int cursor_index = pic.part_count();
	mse.set_xy( 0, 0 );
	
	int last_location = 1;
	loop  
		int responses = response_manager.total_response_count(1);
	until false
	begin
		pic.present();
		mse.poll();
		int x = mse.x();
		int y = mse.y();	
		###display cursor at coordinates
		pic.set_part_x( cursor_index, x );
		pic.set_part_y( cursor_index, y ); 
		###change colors if necessary
		int location = current_button(locations, x, y);
		if location != last_location then
			if (location > 0) then
				menu_text[location].set_background_color(145, 200, 145 );
				menu_text[location].redraw();
				menu_sound.present();
			end;
			if (last_location > 0) then
				menu_text[last_location].set_background_color(44, 91, 143 );
				menu_text[last_location].redraw();
			end;
			last_location = location;
		end;
		if (resp(1, responses)) then
			if (location > 0) then 
			#	menu_text[last_location].set_background_color(44, 91, 143 );
			#	menu_text[last_location].redraw();				
				pic.clear();
				return location 
			end;
			responses = response_manager.total_response_count(1)
		end;
	end;
	return 0;
end;

########Support PCL





sub int stimulus_menu_loop
begin
	array<string>stimulus_menu_options[7] = {"2d Visual Stimuli", "Video Stimuli", "3d Visual Stimuli", "Auditory Stimuli", "Return to Features", "Return to Main Menu", "Exit"};
	int stimulus_menu_choice;
	bool goto_main = false;
	loop stimulus_menu_choice = menu(stimulus_menu_options, "Stimuli")
	until stimulus_menu_choice == 5  || stimulus_menu_choice == 6 || stimulus_menu_choice == 7 
	begin
		if stimulus_menu_choice == 1
		then term.print("choice 1");
		elseif stimulus_menu_choice == 2
		then term.print("choice 2");
		elseif stimulus_menu_choice == 3
		then term.print("choice 3");
		elseif stimulus_menu_choice == 4
		then term.print("choice 4");
		end;
		stimulus_menu_choice = menu(stimulus_menu_options, "Stimuli");
	end;
	return stimulus_menu_choice
end;


sub int response_menu_loop
begin
	array<string>response_menu_options[5] = {"Button Devices", "Axis Devices", "Return to Features", "Return to Main Menu", "Exit"};
	int response_menu_choice;
	loop response_menu_choice = menu(response_menu_options, "Response Devices")
	until response_menu_choice == 3  || response_menu_choice == 4 || response_menu_choice == 5 
	begin
		if response_menu_choice == 1
		then term.print("button\n");
		elseif response_menu_choice == 2
		then term.print("axis\n");
		end;
		response_menu_choice = menu(response_menu_options, "Response Devices");
	end;
	return response_menu_choice
end;


sub bool feature_menu_loop
begin
	array<string>feature_menu_options[7] = {"Response Devices", "Stimuli", "Hardware Interfacing", "Programmability", "Timing", "Return to Main Menu", "Exit"};
	int feature_menu_choice;
	loop feature_menu_choice = menu(feature_menu_options, "Features")
	until feature_menu_choice == 6  
	begin
		if feature_menu_choice == 1
			then int next = response_menu_loop();
			if next == 4 then return false
			elseif next == 5 then return true
			end;			
		elseif feature_menu_choice == 2
			then int next = stimulus_menu_loop();	
			if next == 6 then return false
			elseif next == 7 then return true
			end;		
		elseif feature_menu_choice == 3
			then term.print("choice 3");
		elseif feature_menu_choice == 4 
			then term.print("choice 4");
		elseif feature_menu_choice == 5
			then term.print("choice 5");
		elseif feature_menu_choice == 7
			then return true
		end;
		feature_menu_choice = menu(feature_menu_options, "Features");
	end;
	return false
end;

sub bool audio_menu_loop
begin
	bool exit =false;
	mode = 0;
	array<string>audio_menu_options[3] = {"Audio-only","Audio+Visual","Exit"};
	int audio_menu_choice;
	loop audio_menu_choice = menu(audio_menu_options, "Please choose Audio-only or Audio + Visual condition")
	until mode > 0 || exit
	begin
		if audio_menu_choice == 1
			then mode=1; Modedisp.set_caption("Mode="+audio_menu_options[1]); term.print("Mode=Aud_onl\n");  exit=true;
		elseif audio_menu_choice == 2
			then mode=2; Modedisp.set_caption("Mode="+audio_menu_options[2]); term.print("Mode=Aud_Vid\n");  exit=true;
		elseif audio_menu_choice == 3
			then exit=true;
				end;
		end;
		Listdisp.redraw();
		Modedisp.redraw();
	return false;
end;
			
sub main_menu_loop
begin k=0;
	loop
	string List = " ";
	bool exit = false; 
	array<string>main_menu_options[13] = {"Practice (no sim)", "Practice (easy)", "Practice (difficult)","ELR_L1", "ELR_L2", "ELR_L3","ELR_L4", "ELR_L5","DLR_L1","DLR_L2","DLR_L3","DLR_L4","Exit"};
	main_menu_choice = menu(main_menu_options, "Please choose which list you'd like to run:")
	until k > 0 || exit
	begin
		if main_menu_choice == 1 
			then k=1; Prac=1; NumSent=5; PracN=""; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="prac\\AzB_prac"; VidPrefix="video\\AzB_prac"; audio_menu_loop(); listtype="Prac";
		elseif main_menu_choice == 2
			then k=2; Prac=1; NumSent=5; PracN=""; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="prac\\AzB_prac_15chan40dB"; VidPrefix="video\\AzB_prac"; audio_menu_loop(); listtype="Prac";
		elseif main_menu_choice == 3
			then k=3; Prac=1; NumSent=5; PracN=""; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="prac\\AzB_prac_15chan10dB"; VidPrefix="video\\AzB_prac"; audio_menu_loop(); listtype="Prac";
		elseif main_menu_choice == 4
			then k=1; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="LR_vocoded_15ch_10dBoc\\ELR_"; VidPrefix="video\\ELR_"; audio_menu_loop(); listtype="ELR";
		elseif main_menu_choice == 5
			then k=2; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="LR_vocoded_15ch_10dBoc\\ELR_"; VidPrefix="video\\ELR_"; audio_menu_loop(); listtype="ELR";
		elseif main_menu_choice == 6
			then k=3; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="LR_vocoded_15ch_10dBoc\\ELR_"; VidPrefix="video\\ELR_"; audio_menu_loop(); listtype="ELR";
		elseif main_menu_choice == 7
			then k=4; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="LR_vocoded_15ch_10dBoc\\ELR_"; VidPrefix="video\\ELR_"; audio_menu_loop(); listtype="ELR";
		elseif main_menu_choice == 8
			then k=5; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="LR_vocoded_15ch_10dBoc\\ELR_"; VidPrefix="video\\ELR_"; audio_menu_loop(); listtype="ELR";
		elseif main_menu_choice == 9
			then k=1; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="LR_vocoded_15ch_10dBoc\\DLR_"; VidPrefix="video\\DLR_"; audio_menu_loop(); listtype="DLR";
		elseif main_menu_choice == 10
			then k=2; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="LR_vocoded_15ch_10dBoc\\DLR_"; VidPrefix="video\\DLR_"; audio_menu_loop(); listtype="DLR";
		elseif main_menu_choice == 11
			then k=3; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="LR_vocoded_15ch_10dBoc\\DLR_"; VidPrefix="video\\DLR_"; audio_menu_loop(); listtype="DLR";
		elseif main_menu_choice == 12
			then k=4; List=main_menu_options[k]; Listdisp.set_caption("List="+List); term.print("List="+List+"\n"); Prefix="LR_vocoded_15ch_10dBoc\\DLR_"; VidPrefix="video\\DLR_"; audio_menu_loop(); listtype="DLR";
		elseif main_menu_choice == 13
		then exit = true;
		
	end;
Listdisp.redraw();
	Modedisp.redraw();
		if exit then break end;
	end;
end;

main_menu_loop();


text1.set_caption("Enter SNR:");
text1.redraw();
front_trial.present();
	loop
	int pos=0;
	until 
	pos==1
	begin
		system_keyboard.set_delimiter( '\n' );
		 string input = (system_keyboard.get_input( pic1, input_text ));
		 string snr=input;
		int SNR = int(snr);
		SNR = (SNR+20);
		double snrCode = (double(SNR)*0.01);
		front_event.set_event_code("SNR input:"+string(SNR));
		term.print("SNR input:"+input+"\n");
		if is_int(snr) then
			if (int(snr) > -21) && (int(snr)  < 21) then
				backNoise_R.set_attenuation(snrCode);
				backNoise_L.set_attenuation(snrCode);
				SNRdisp.set_caption("SNR="+input);
				SNRdisp.redraw();
			pos=1;
			elseif (int(snr) < -20) || (int(snr)  > 20)  then
				term.print( "Please enter a value between -20 and 20 SNR\n" );
				notify_pic.set_part(1, SNRwrong);
				notify_trial.present();		
				pos=0;
				term.print( " " );
			end;
		else	
			term.print( "Please enter a valid number\n" );
			notify_pic.set_part(1, Numwrong);
			notify_trial.present();		
			pos=0;
			
		end;
	
		
		end;

#

text1.set_caption(" ");
text1.redraw();
system_keyboard.clear_keypresses( ) ;
notify_text.set_caption("Please repeat what you hear.\n\nPress Enter to begin.");
notify_text.redraw();
notify_trial.present();
#
if main_menu_choice >3 then
backNoise_L.present();
backNoise_R.present();

backNoise_trial.present(); #background noise not needed
end;
#
notify_text.set_caption(" ");
notify_text.redraw();
#
		term.print("Number of sentences:"+string(NumSent)+"\n");
		array<string> results[2][NumSent];

loop
	int j=1;
	string ListN=" ";
	string SentN=" ";
	double dur =double(2000);

until
	j > NumSent
begin

	if Prac == 1 then
	ListN = PracN;
	elseif k < 10 then 
	ListN = ("0"+string(k))
	elseif k > 9 then
	 ListN = string(k);

	end;

	if j < 10 then  SentN = ("0"+string(j))
	else
	 SentN = string(j);
	end;

	if mode == 1	then
		string sndName="S:\\Projects\\CIRL\\Experiments\\2013\\13_05_lipreading\\Presentation\\stimuli\\"+Prefix+ListN+"_"+SentN+".wav";
		wav1.set_filename(sndName);
		wav1.load();
		dur=double(wav1.duration());
		term.print(sndName);
					
		feedback.set_caption(" ");
		feedback.redraw();
		sound_trial.present();

		end;	
	
	if mode == 2	then
		vid.set_use_audio(false);
		term.print(Prefix+ListN+SentN+"\n");
		term.print("List:"+ListN+"\n");
		string vidName="S:\\Projects\\CIRL\\Experiments\\2013\\13_05_lipreading\\Presentation\\stimuli\\"+VidPrefix+ListN+"_"+SentN+".avi";
		vid.set_filename(vidName);
		string sndName="S:\\Projects\\CIRL\\Experiments\\2013\\13_05_lipreading\\Presentation\\stimuli\\"+Prefix+ListN+"_"+SentN+".wav";
		term.print("snd:"+sndName+"\n");
		wav1.set_filename(sndName);
		wav1.load();
		term.print(sndName);
		feedback.set_caption(" ");
		feedback.redraw();
		vid_trial.present();
		end;

	
	feedback_trial.present();
	system_keyboard.set_delimiter( '\n' );
	system_keyboard.CASE_SHIFT;
	string feedback_input = (system_keyboard.get_input( feedback_pic, feedback));
	string answer = feedback_input;
	feedback_event.set_event_code("Ans:"+answer);
	term.print(listtype+string(k)+"T"+string(j)+":"+answer+"\n");
	results[1][j]=(Prefix+"_"+ListN+"_"+SentN);
	results[2][j]=(answer);
	j=j+1;
end;
notify_text.set_caption("The block is complete \n\nPress Enter to exit");
notify_text.redraw();
notify_trial.present();
wav1.unload();
noise_R.unload();
noise_L.unload();



#--------------Output File#
string logfilename = logfile.filename();
string SubID= logfile.subject();	
output_file file = new output_file;
file.open( SubID+"_LRnorm_results.txt");


#set up titles#
#
file.print("Subject:"+SubID+"\t\t");
file.print("\n");
file.print("Log file:"+logfilename+"\t\t");
file.print("\n");
file.print("Date:"+date_time()+"\t\t");
file.print("\n");
#/
file.print("Stim Name\t\t");
file.print("Answer\t\t");
file.print("\n");

loop
   int o = 1
until
   o > NumSent
begin
   file.print(results[1][o]+"\t");
	file.print(results[2][o]+"\t");
	file.print("\n");
   o = o + 1
end;

#---------------#
