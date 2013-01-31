package com.archermind.ashare.player.service;

interface IPlayService {
    void IPlay(String mediaType);
    void IPause();
    void IStop();
    void ISeek(int whereto);
    void ISetVolume(int volume);
    String IGetPlayerState();
    int IGetDuration();
    int IGetCurrentPosition();
    void ISetUrl(String uri,String uriMetaData);
}