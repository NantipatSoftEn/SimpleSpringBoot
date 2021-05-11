import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ChatService } from 'src/app/services/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
})
export class ChatComponent implements OnInit {
  private stompClient: any;
  private CHANNEL = `chat`;
  private ENDPOINT = `http://localhost:8080/socket`;
  isConected = false;

  chatFormGroup: FormGroup = new FormGroup({
    message: new FormControl('', Validators.required),
  });
  constructor(private chatService: ChatService) {}

  ngOnInit(): void {
    this.connectWebsocket();
  }

  private connectWebsocket() {
    let ws = new SockJS(this.ENDPOINT);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, () => {
      that.isConected = true;
      that.subscribeToGlobalChat();
    });
  }

  private subscribeToGlobalChat() {
    this.stompClient.subscribe(this.CHANNEL, (message: any) => {
      console.log(message);
    });
  }

  onSubmit() {
    let message = this.chatFormGroup.controls.message.value;
    if (!this.isConected) {
      alert(`please connect to WebSocket`);
      return;
    }
    //alert(`Ready to send`);
    this.chatService.postMessage(message).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
