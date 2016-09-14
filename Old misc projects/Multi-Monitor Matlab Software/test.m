noise1=['S:\Projects\CIRL\AudioFiles\OtherAudio\Baby BIo\Baby BIo\IEEEMale_concat_1.wav'];
noise2=['S:\Projects\CIRL\AudioFiles\OtherAudio\Baby BIo\Baby BIo\IEEEMale_concat_2.wav'];

[BackN1,Fs] = audioread(noise1);
    sound (BackN1);
[BackN2,Fs] = audioread(noise2);
    sound (BackN2);


while i <16
      avfile_path = ['S:\Projects\CIRL\AudioFiles\OtherAudio\Baby BIo\Baby BIo\'];
    
 if ListN<10
     tempnumber= ['0',ListN];
 else
     tempnumber =ListN;
 end;
    
 if i<10   
tempfilename=['AzKid_',tempnumber,'_',num2str(i),'.wav']
 else
 tempfilename=['15ASUAV_',tempnumber,'_',num2str(i),'.wav']
 end;
    
    
    mtp2.PlaySpeed = .9;
  

    [y,Fs] = audioread(tempfilename)
    sound (y)
    
end;