clear all
%eval(['edit ',mfilename]);

%keyboard

clearAllMediaToolsInstances;
close all;
SpkrN= input('Enter speaker number 1-8: ','s');
SpkrN= str2num(SpkrN);


%Calculate values for volume%%%
volnoise= 1;
wavfile_path = ['S:\Projects\CIRL\AudioFiles\OtherAudio\speech tokens\utility\'];
OUTPUT_DEV= MediaToolsOutputDevices('ASIO');

% A1=readA([avfile_path,'sheshere_49.wav']);


Noise1 = mtplayer([wavfile_path,'PinkNoisefromRSPACE_84dB.wav'], OUTPUT_DEV);



Noise1.LoopAVSource = 'on';


%vol = 0; %to silence

%set speaker output
if SpkrN==1
    Noise1.SourceToOutputAudioMixing = volnoise*[1 0 0 0 0 0 0 0 0 0 0 0];
elseif SpkrN==2
    Noise1.SourceToOutputAudioMixing = volnoise*[0 1 0 0 0 0 0 0 0 0 0 0];
elseif SpkrN==3
    Noise1.SourceToOutputAudioMixing = volnoise*[0 0 1 0 0 0 0 0 0 0 0 0];
elseif SpkrN==4
    Noise1.SourceToOutputAudioMixing = volnoise*[0 0 0 1 0 0 0 0 0 0 0 0];
elseif SpkrN==5
    Noise1.SourceToOutputAudioMixing = volnoise*[0 0 0 0 1 0 0 0 0 0 0 0];
elseif  SpkrN==6
    Noise1.SourceToOutputAudioMixing = volnoise*[0 0 0 0 0 1 0 0 0 0 0 0];
elseif  SpkrN==7
    Noise1.SourceToOutputAudioMixing = volnoise*[0 0 0 0 0 0 1 0 0 0 0 0];
elseif SpkrN==8
    disp('yes');
    Noise1.SourceToOutputAudioMixing = volnoise*[0 0 0 0 0 0 0 1 0 0 0 0];

end

play(Noise1);
pause(25);

clearAllMediaToolsInstances;
close all;
