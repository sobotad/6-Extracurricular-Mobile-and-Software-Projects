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

dBtarget=dBtarget+2; %fine tune soundfile to be right level (b/c measurements were done with max not avg)
dBnoise3=dBnoise+3; %fine tune soundfile to be right level (b/c measurements were done with max not avg)
dBnoise7=dBnoise+2; %fine tune soundfile to be right level (b/c measurements were done with max not avg)

dBnoise3=dBnoise-1; %subtract 1dB so that sum of other noise equals target noise level
dBnoise7=dBnoise-1; %subtract 1dB so that sum of other noise equals target noise level

%Calculate values for volume%%%
voltarget= 10^((0.05093*dBtarget)-4.299); %Babybio formula 8/2/2016
disp('voltarget=');
disp(voltarget);


volnoise3= 10^((0.05101*dBnoise3)-4.688); %IEEEMale_concat_2 formula 8/2/2016
disp('volnoise3=');
disp(volnoise3);

volnoise7= 10^((0.05069*dBnoise7)-4.341); %IEEEMale_concat_1 formula 8/2/2016
disp('volnoise7=');
disp(volnoise7);


FigHandle = figure('units','pixels', 'Color', 'black','Position', [1920 80 5770 1080]);
set(FigHandle, 'menubar', 'none');
FigHandle2= figure('units','pixels','Position', [700 500 450 400]);

[mfile_path,name,ext] = fileparts(mfilename('fullpath'));

avfile_path = ['C:\speech tokens\BabyBio\'];

wavfile_path = ['C:\speech tokens\BabyBio\'];
OUTPUT_DEV= MediaToolsOutputDevices('ASIO');

% A1=readA([avfile_path,'sheshere_49.wav']);


Noise3 = mtplayer([avfile_path,'IEEEMale_concat_2.wav'], OUTPUT_DEV);
Noise7 = mtplayer([avfile_path,'IEEEMale_concat_1.wav'], OUTPUT_DEV);


Noise3.LoopAVSource = 'on';
Noise7.LoopAVSource = 'on';

%vol = 0; %to silence

Noise3.SourceToOutputAudioMixing = volnoise3*[0 0 1 0 0 0 0 0 0 0 0 0]; %coming out of spkr3
Noise7.SourceToOutputAudioMixing = volnoise7*[0 0 0 0 0 0 1 0 0 0 0 0]; %coming out of spkr7


play(Noise3);
play(Noise7);


i=1;
while i < 21

 if ListN<10
     tempnumber= ['',ListN];
 else
     tempnumber =['0',ListN];
 end;
    
 if i<10   
tempfilename=['AzKid_',tempnumber,'_0',num2str(i),'.wav'];
 else
 tempfilename=['AzKid_',tempnumber,'_',num2str(i),'.wav'];
 end;
    
mtp2 = mtplayer([avfile_path,tempfilename], OUTPUT_DEV,'ParentFigureHandle',FigHandle,'DisplayPosition',[1281 1 1280 1024]);




 mtp2.EnableAudio = 'on';
 mtp2.SourceToOutputAudioMixing = [0 1 0 0 0 0 0 0 0 0 0 0];


%  voltarget = 0; %to silence

mtp2.SourceToOutputAudioMixing(mtp2.SourceToOutputAudioMixing~=0) = voltarget;
mtp2.PlaySpeed = .9;






play(mtp2);
while isplaying(mtp2)== 1 %checks if audio is playing and doesn't continue until done
end

pause(3);
delete(mtp2);



 i=i+1;
 

 
end;

clearAllMediaToolsInstances;
close all;
