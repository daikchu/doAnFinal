/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { KhuyenMaiService } from 'app/entities/khuyen-mai/khuyen-mai.service';
import { IKhuyenMai, KhuyenMai } from 'app/shared/model/khuyen-mai.model';

describe('Service Tests', () => {
    describe('KhuyenMai Service', () => {
        let injector: TestBed;
        let service: KhuyenMaiService;
        let httpMock: HttpTestingController;
        let elemDefault: IKhuyenMai;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(KhuyenMaiService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new KhuyenMai('ID', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, 0, 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        timeStart: currentDate.format(DATE_TIME_FORMAT),
                        timeEnd: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find('123')
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a KhuyenMai', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 'ID',
                        timeStart: currentDate.format(DATE_TIME_FORMAT),
                        timeEnd: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        timeStart: currentDate,
                        timeEnd: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new KhuyenMai(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a KhuyenMai', async () => {
                const returnedFromService = Object.assign(
                    {
                        tieuDe: 'BBBBBB',
                        noiDung: 'BBBBBB',
                        loaiApDung: 'BBBBBB',
                        timeStart: currentDate.format(DATE_TIME_FORMAT),
                        timeEnd: currentDate.format(DATE_TIME_FORMAT),
                        mucKM: 1,
                        status: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        timeStart: currentDate,
                        timeEnd: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of KhuyenMai', async () => {
                const returnedFromService = Object.assign(
                    {
                        tieuDe: 'BBBBBB',
                        noiDung: 'BBBBBB',
                        loaiApDung: 'BBBBBB',
                        timeStart: currentDate.format(DATE_TIME_FORMAT),
                        timeEnd: currentDate.format(DATE_TIME_FORMAT),
                        mucKM: 1,
                        status: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        timeStart: currentDate,
                        timeEnd: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a KhuyenMai', async () => {
                const rxPromise = service.delete('123').subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
