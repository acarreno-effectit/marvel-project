import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarvelServiceService {

  private endpoint: string = 'http://localhost:8080/v1/api/marvel';

  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Basic dXNlcjpUZXN0MTIzKg=='
  });

  constructor(private http: HttpClient) { }

  getHeroes(): Observable<any> {
    const headers = this.headers;
    return this.http.get(this.endpoint, { headers });
  }

  getHeroById(id: any): Observable<any> {
    const headers = this.headers;
    return this.http.get(this.endpoint + '/' + id, { headers });
  }
}
