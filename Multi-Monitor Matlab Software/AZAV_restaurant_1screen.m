clear all
%eval(['edit ',mfilename]);

%keyboard

clearAllMediaToolsInstances;
close all;
ListN= input('Enter List Number 1-15: ','s');
dBAZAV= input('Enter decibel level of sentences:');
if dBAZAV >= 90
    disp('***ERROR: Please enter a level below 90dB****');
    dBAZAV= input('Enter decibel level of sentences:');
end
dBnoise= input ('Enter dB level of background noise (SNR -10 would mean noise is 10dB louder than sentences):');
if dBnoise >= 90
    disp('***ERROR: Please enter a level below 90dB****');
    dBnoise= input ('Enter dB level of background noise (SNR -10 would mean noise is 10dB louder than sentences):');
end
dBhere=dBAZAV;

%Calculate values for volume%%%
volAZAV= 10^((0.05026*dBAZAV)-3.588);
disp('volAZAV=');
disp(volAZAV);


volnoise= 10^((0.05207*dBnoise)-3.831);
disp('volnoise=');
disp(volnoise);




FigHandle = figure('units','pixels', 'Color', 'black','Position', [1920 80 5770 1080]);
set(FigHandle, 'menubar', 'none');
FigHandle2= figure('units','pixels','Position', [700 500 450 400]);

[mfile_path,name,ext] = fileparts(mfilename('fullpath'));

avfile_path = ['S:\Projects\CIRL\AudioFiles\OtherAudio\Baby BIo\Baby BIo\'];

wavfile_path = ['S:\Projects\CIRL\AudioFiles\OtherAudio\Baby BIo\Baby BIo\'];
OUTPUT_DEV= MediaToolsOutputDevices('ASIO');

% A1=readA([avfile_path,'sheshere_49.wav']);


Noise4 = mtplayer([avfile_path,'IEEEMale_concat_2.wav'], OUTPUT_DEV);
Noise8 = mtplayer([avfile_path,'IEEEMale_concat_1.wav'], OUTPUT_DEV);


Noise4.LoopAVSource = 'on';
Noise8.LoopAVSource = 'on';

%vol = 0; %to silence

Noise4.SourceToOutputAudioMixing = volnoise*[0 0 0 1 0 0 0 0 0 0 0 0];
Noise8.SourceToOutputAudioMixing = volnoise*[0 0 0 0 0 0 0 1 0 0 0 0];


play(Noise4);
play(Noise8);


i=1;
while i < 21

 if ListN<10
     tempnumber= ['0',ListN];
 else
     tempnumber =['0',ListN];
 end;
    
 if i<10   
tempfilename=['AzKid_',tempnumber,'_0',num2str(i),'.wav']
 else
 tempfilename=['AzKid_',tempnumber,'0_',num2str(i),'.wav']
 end;
    
mtp2 = mtplayer([avfile_path,tempfilename], OUTPUT_DEV,'ParentFigureHandle',FigHandle,'DisplayPosition',[1281 1 1280 1024]);




 mtp2.EnableAudio = 'on';
 mtp2.SourceToOutputAudioMixing = [0 1 0 0 0 0 0 0 0 0 0 0];


%  volAZAV = 0; %to silence

mtp2.SourceToOutputAudioMixing(mtp2.SourceToOutputAudioMixing~=0) = volAZAV;
mtp2.PlaySpeed = .9;






play(mtp2);
pause(5);
delete(mtp2);



 i=i+1;
 

 
end;

clearAllMediaToolsInstances;
close all;
