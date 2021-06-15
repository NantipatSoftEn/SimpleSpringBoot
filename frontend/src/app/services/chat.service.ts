import { HttpClient } from '@angular/common/http';
import { IChatResponse } from '../interfaces/i-chat-response';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const URL = 'http://localhost:8080/chat/message';
@Injectable({
  providedIn: 'root',
})
export class ChatService {
  constructor(private http: HttpClient) {}

  postMessage(message: string): Observable<IChatResponse> {
    let body = {
      message: message,
    };
    return this.http.post<IChatResponse>(URL, body);
  }
}
