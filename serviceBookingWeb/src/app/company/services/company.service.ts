import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../basic/services/storage/user-storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http:HttpClient) { }
  postAd(adDto:any):Observable<any>{
    const userId = UserStorageService.getUserId();
    return this.http.post(BASIC_URL + `api/company/ad/${userId}`,adDto,{
      headers: this.createAuthorizationHeader()
    })
  }

  getAllAdsByUserId():Observable<any>{
    const userId = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/company/ads/${userId}`,{
      headers: this.createAuthorizationHeader()
    })
  }

  createAuthorizationHeader():HttpHeaders{
    let authHedears : HttpHeaders = new HttpHeaders();
    return authHedears.set(
      'Authorization',
      'Bearer '+ UserStorageService.getToken()
    )
  }
}
