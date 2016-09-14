clear all
%eval(['edit ',mfilename]);

%keyboard

clearAllMediaToolsInstances;
close all;
ListN= input('Enter List Number 1-15: ','s');
dBtarget= input('Enter decibel level of sentences:');
if dBtarget >= 90
    disp('***ERROR: Please enter a level below 90dB****');
    dBtarget= input('Enter decibel level of sentences:');
end
dBnoise= input ('Enter dB level of background noise (SNR -10 would mean noise is 10dB louder than sentences):');
if dBnoise >= 90
    disp('***ERROR: Please enter a level below 90dB****');
    dBnoise= input ('Enter dB level of background noise (SNR -10 would mean noise is 10dB louder than sentences):');
end
dBhere=dBtarget;

%Calculate values for volume%%%
voltarget= 10^((0.05026*dBtarget)-3.588); %AZAV calc 8/1/2016
disp('voltarget=');
disp(voltarget);


volnoise= 10^((0.04912*dBnoise)-3.536); %Lous restaurant calc 8/1/2016
disp('volnoise=');
disp(volnoise);

FigHandle = figure('units','pixels', 'Color', 'black','Position', [1920 80 5770 1080]);
set(FigHandle, 'menubar', 'none');
FigHandle2= figure('units','pixels','Position', [700 500 450 400]);

[mfile_path,name,ext] = fileparts(mfilename('fullpath'));

avfile_path = ['C:\Program Files\Sensimetrics\AVFiles\'];

wavfile_path = ['C:\Program Files\Sensimetrics\AVFiles\small\'];
OUTPUT_DEV= MediaToolsOutputDevices('ASIO');

% A1=readA([avfile_path,'sheshere_49.wav']);


Noise1 = mtplayer([avfile_path,'1_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise2 = mtplayer([avfile_path,'2_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise3 = mtplayer([avfile_path,'3_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise4 = mtplayer([avfile_path,'4_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise5 = mtplayer([avfile_path,'5_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise6 = mtplayer([avfile_path,'6_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise7 = mtplayer([avfile_path,'7_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise8 = mtplayer([avfile_path,'8_15_20_rev1-48000-02.wav'], OUTPUT_DEV);

Noise1.LoopAVSource = 'on';
Noise2.LoopAVSource = 'on';
Noise3.LoopAVSource = 'on';
Noise4.LoopAVSource = 'on';
Noise5.LoopAVSource = 'on';
Noise6.LoopAVSource = 'on';
Noise7.LoopAVSource = 'on';
Noise8.LoopAVSource = 'on';

%vol = 0; %to silence
Noise1.SourceToOutputAudioMixing = volnoise*[1 0 0 0 0 0 0 0 0 0 0 0];
Noise2.SourceToOutputAudioMixing = volnoise*[0 1 0 0 0 0 0 0 0 0 0 0];
Noise3.SourceToOutputAudioMixing = volnoise*[0 0 1 0 0 0 0 0 0 0 0 0];
Noise4.SourceToOutputAudioMixing = volnoise*[0 0 0 1 0 0 0 0 0 0 0 0];
Noise5.SourceToOutputAudioMixing = volnoise*[0 0 0 0 1 0 0 0 0 0 0 0];
Noise6.SourceToOutputAudioMixing = volnoise*[0 0 0 0 0 1 0 0 0 0 0 0];
Noise7.SourceToOutputAudioMixing = volnoise*[0 0 0 0 0 0 1 0 0 0 0 0];
Noise8.SourceToOutputAudioMixing = volnoise*[0 0 0 0 0 0 0 1 0 0 0 0];

play(Noise1);
play(Noise2);
play(Noise3);
play(Noise4);
play(Noise5);
play(Noise6);
play(Noise7);
play(Noise8);


i=1;
while i < 16

 if i<10   
tempfilename=['15ASUAV_',ListN,'_0',num2str(i),'.avi']
 else
 tempfilename=['15ASUAV_',ListN,'_',num2str(i),'.avi']
 
 end;
    
mtp2 = mtplayer([avfile_path,tempfilename], OUTPUT_DEV,'ParentFigureHandle',FigHandle,'DisplayPosition',[1281 1 1280 1024]);



mtp2.VideoDelayReAudioMsec = -500;

 mtp2.EnableAudio = 'on';
 mtp2.SourceToOutputAudioMixing = [0 0 0 0 0 0 0 0 0 0 0 0; 1 0 0 0 0 0 0 0 0 0 0 0];


%  volAZAV = 0; %to silence

mtp2.SourceToOutputAudioMixing(mtp2.SourceToOutputAudioMixing~=0) = voltarget;



 mtp2.PlaySpeed = .9;



play(mtp2);
pause(5);
delete(mtp2);



 i=i+1;
 

 
end;

clearAllMediaToolsInstances;
close all;
