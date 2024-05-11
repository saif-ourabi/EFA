import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CoursService {

  
  constructor(private http: HttpClient) { }
  private url="http://localhost:8081/api/files"

  getefiles(): Observable<any> {
    return this.http.get(this.url);
}

deleteFile(id: number): Observable<void> {
  return this.http.delete<void>(`${this.url}/${id}`);
}

addFile(fileData: any): Observable<any> {
  return this.http.post<any>(`${this.url}/addFile`, fileData);
}
updateFile(id: string, fileData: any): Observable<any> {
  return this.http.put<any>(`${this.url}/updateFile/${id}`, fileData);
}
GetChartInfo()
{
   return this.http.get ("http://localhost:8081/api/user/countByRole");
}



}
