import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MidiaServiceService {

  constructor(private http: HttpClient) { }

  uploadPhoto(file: File): Observable<any> {
    let data: FormData = new FormData()
    data.append('file', file)
    data.append('upload_preset', 'rvmiwnpa')
    data.append('cloud_name', 'guimarcionilo')
    
    return this.http.post('https://api.cloudinary.com/v1_1/guimarcionilo/image/upload', data)
  }
}
