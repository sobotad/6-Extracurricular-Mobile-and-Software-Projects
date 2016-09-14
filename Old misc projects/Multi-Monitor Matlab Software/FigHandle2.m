function varargout = FigHandle2(varargin)
%FIGHANDLE2 M-file for FigHandle2.fig
%      FIGHANDLE2, by itself, creates a new FIGHANDLE2 or raises the existing
%      singleton*.
%
%      H = FIGHANDLE2 returns the handle to a new FIGHANDLE2 or the handle to
%      the existing singleton*.
%
%      FIGHANDLE2('Property','Value',...) creates a new FIGHANDLE2 using the
%      given property value pairs. Unrecognized properties are passed via
%      varargin to FigHandle2_OpeningFcn.  This calling syntax produces a
%      warning when there is an existing singleton*.
%
%      FIGHANDLE2('CALLBACK') and FIGHANDLE2('CALLBACK',hObject,...) call the
%      local function named CALLBACK in FIGHANDLE2.M with the given input
%      arguments.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help FigHandle2

% Last Modified by GUIDE v2.5 15-Apr-2015 13:16:19

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @FigHandle2_OpeningFcn, ...
                   'gui_OutputFcn',  @FigHandle2_OutputFcn, ...
                   'gui_LayoutFcn',  [], ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
   gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT
load Set1_AZAV.mat;
Setchoice = Set1_AZAV;

% --- Executes just before FigHandle2 is made visible.
function FigHandle2_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   unrecognized PropertyName/PropertyValue pairs from the
%            command line (see VARARGIN)

% Choose default command line output for FigHandle2
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% UIWAIT makes FigHandle2 wait for user response (see UIRESUME)
% uiwait(handles.figure1);


% --- Outputs from this function are returned to the command line.
function varargout = FigHandle2_OutputFcn(hObject, eventdata, handles)
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;


% --- Executes on selection change in popupmenu1.
function popupmenu1_Callback(hpopup, eventdata, handles)
% hObject    handle to popupmenu1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
contents = cellstr(get(hpopup,'String'));
% Hints: contents = cellstr(get(hObject,'String')) returns popupmenu1 contents as cell array
%        contents{get(hObject,'Value')} returns selected item from popupmenu1


% --- Executes during object creation, after setting all properties.
function popupmenu1_CreateFcn(hObject, eventdata, handles)
% hObject    handle to popupmenu1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: popupmenu controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in Playbutton.
function Playbutton_Callback(hObject, eventdata, handles)
% hplay   handle to Playbutton (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)


Setchoice=get(handles.listbox1,'Value');

if strcmp(Setchoice,'Set1') == 1
    ActiveSet=Set1_AZAV
end;

FigHandle = figure('units','pixels', 'Color', 'black','Position', [1926 1 5770 1024]);

%index_1 = 1;
%files_1 = {'sample1.mov','sample2.mov'};
%index_2 = 1;
%files_2 = {'sample3.mov','sample4.mov'};
%index_3 = 1;
%files_3 = {'sample5.mov','sample6.mov'};
set(hObject,'Enable', 'off');

load Set1_AZAV.mat
[mfile_path,name,ext] = fileparts(mfilename('fullpath'));

avfile_path = [mfile_path, '\AVFiles\'];

wavfile_path = [mfile_path, '\AVFiles\'];
OUTPUT_DEV= MediaToolsOutputDevices('ASIO');

% A1=readA([avfile_path,'sheshere_44.wav']);


Noise1 = mtplayer([avfile_path,'1_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise2 = mtplayer([avfile_path,'2_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise3 = mtplayer([avfile_path,'3_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise4 = mtplayer([avfile_path,'4_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise5 = mtplayer([avfile_path,'5_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise6 = mtplayer([avfile_path,'6_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise7 = mtplayer([avfile_path,'7_15_20_rev1-48000-02.wav'], OUTPUT_DEV);
Noise8 = mtplayer([avfile_path,'8_15_20_rev1-48000-02.wav'], OUTPUT_DEV);

Noise1.SourceToOutputAudioMixing = [1 0 0 0 0 0 0 0 0 0 0 0];
Noise2.SourceToOutputAudioMixing = [0 1 0 0 0 0 0 0 0 0 0 0];
Noise3.SourceToOutputAudioMixing = [0 0 1 0 0 0 0 0 0 0 0 0];
Noise4.SourceToOutputAudioMixing = [0 0 0 1 0 0 0 0 0 0 0 0];
Noise5.SourceToOutputAudioMixing = [0 0 0 0 1 0 0 0 0 0 0 0];
Noise6.SourceToOutputAudioMixing = [0 0 0 0 0 1 0 0 0 0 0 0];   
Noise7.SourceToOutputAudioMixing = [0 0 0 0 0 0 1 0 0 0 0 0];
Noise8.SourceToOutputAudioMixing = [0 0 0 0 0 0 0 1 0 0 0 0];   



vol = .05;
Noise1.SourceToOutputAudioMixing(Noise1.SourceToOutputAudioMixing~=0) = vol;
Noise2.SourceToOutputAudioMixing(Noise2.SourceToOutputAudioMixing~=0) = vol;
Noise3.SourceToOutputAudioMixing(Noise3.SourceToOutputAudioMixing~=0) = vol;
Noise4.SourceToOutputAudioMixing(Noise4.SourceToOutputAudioMixing~=0) = vol;
Noise5.SourceToOutputAudioMixing(Noise5.SourceToOutputAudioMixing~=0) = vol;
Noise6.SourceToOutputAudioMixing(Noise6.SourceToOutputAudioMixing~=0) = vol;
Noise7.SourceToOutputAudioMixing(Noise7.SourceToOutputAudioMixing~=0) = vol;
Noise8.SourceToOutputAudioMixing(Noise8.SourceToOutputAudioMixing~=0) = vol;

play(Noise1);
play(Noise2);
play(Noise3);
play(Noise4);
play(Noise5);
play(Noise6);
play(Noise7);
play(Noise8);


i=1;
while i < 49
    
    

mtphere = mtplayer([avfile_path,'sheshere_48.wav'], OUTPUT_DEV,'ParentFigureHandle',FigHandle,'DisplayPosition',[5200 1 10 10]);   
mtp1 = mtplayer([avfile_path,ActiveSet{i,1}], OUTPUT_DEV,'ParentFigureHandle',FigHandle,'DisplayPosition',[0 1 1280 1024]);
mtp2 = mtplayer([avfile_path,ActiveSet{i,2}], OUTPUT_DEV,'ParentFigureHandle',FigHandle,'DisplayPosition',[1281 1 1280 1024]);
mtp3 = mtplayer([avfile_path,ActiveSet{i,3}], OUTPUT_DEV,'ParentFigureHandle',FigHandle,'DisplayPosition',[2561 1 1280 1024]);
mtp4 = mtplayer([avfile_path,ActiveSet{i,4}], OUTPUT_DEV,'ParentFigureHandle',FigHandle,'DisplayPosition',[3841 1 1280 1024]);



mtp1.VideoDelayReAudioMsec = -500;
mtp2.VideoDelayReAudioMsec = -500;
mtp3.VideoDelayReAudioMsec = -500;
mtp4.VideoDelayReAudioMsec = -500;




if Set1_AZAV{i,5} == 1
 mtp1.EnableAudio = 'on';
 mtp2.EnableAudio = 'off';
 mtp3.EnableAudio = 'off';
 mtp4.EnableAudio = 'off';
 mtp1.SourceToOutputAudioMixing = [0 0 0 0 0 0 0 0 0 0 0 0; 0 0 0 0 0 0 1 0 0 0 0 0];
 mtphere.SourceToOutputAudioMixing = [0 0 0 0 0 0 1 0 0 0 0 0];
elseif Set1_AZAV{i,5} == 2
 mtp1.EnableAudio = 'off';
 mtp2.EnableAudio = 'on';
 mtp3.EnableAudio = 'off';
 mtp4.EnableAudio = 'off';
 mtp2.SourceToOutputAudioMixing = [0 0 0 0 0 0 0 0 0 0 0 0; 0 0 0 0 0 0 0 1 0 0 0 0];
 mtphere.SourceToOutputAudioMixing = [0 0 0 0 0 0 0 1 0 0 0 0];
elseif Set1_AZAV{i,5} == 3
 mtp1.EnableAudio = 'off';
 mtp2.EnableAudio = 'off';
 mtp3.EnableAudio = 'on';
 mtp4.EnableAudio = 'off';
mtp3.SourceToOutputAudioMixing = [0 0 0 0 0 0 0 0 0 0 0 0; 0 1 0 0 0 0 0 0 0 0 0 0];
mtphere.SourceToOutputAudioMixing = [0 1 0 0 0 0 0 0 0 0 0 0];
elseif Set1_AZAV{i,5} == 4
 mtp1.EnableAudio = 'off';
 mtp2.EnableAudio = 'off';
 mtp3.EnableAudio = 'off';
 mtp4.EnableAudio = 'on';
 mtp4.SourceToOutputAudioMixing = [0 0 0 0 0 0 0 0 0 0 0 0; 0 0 1 0 0 0 0 0 0 0 0 0];
 mtphere.SourceToOutputAudioMixing = [0 0 1 0 0 0 0 0 0 0 0 0];
end;

vol = .08;
volNoise = .01;
mtphere.SourceToOutputAudioMixing( mtphere.SourceToOutputAudioMixing~=0) = volNoise;
mtp1.SourceToOutputAudioMixing(mtp1.SourceToOutputAudioMixing~=0) = vol;
mtp2.SourceToOutputAudioMixing(mtp2.SourceToOutputAudioMixing~=0) = vol;
mtp3.SourceToOutputAudioMixing(mtp3.SourceToOutputAudioMixing~=0) = vol;
mtp4.SourceToOutputAudioMixing(mtp4.SourceToOutputAudioMixing~=0) = vol;

playblocking(mtphere);
play(mtp1);
play(mtp2);
play(mtp3);
play(mtp4);  
pause(5);
delete(mtphere);
delete(mtp1);
delete(mtp2);
delete(mtp3);
delete(mtp4);


 i=i+1;
end;
set(hObject,'Enable', 'on');


% --- Executes on selection change in listbox1.
function listbox1_Callback(hObject, eventdata, handles)
% hObject    handle to listbox1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: contents = cellstr(get(hObject,'String')) returns listbox1 contents as cell array
%        contents{get(hObject,'Value')} returns selected item from listbox1
% contents = cellstr(get(hObject,'String'));
% Setchoice=contents{get(hObject,'Value')};
Setchoice=get(hObject,'Value')


% --- Executes during object creation, after setting all properties.
function listbox1_CreateFcn(hObject, eventdata, handles)
% hObject    handle to listbox1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: listbox controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in Quitbutton.
function Quitbutton_Callback(hObject, eventdata, handles)
% hObject    handle to Quitbutton (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
close(gcf);