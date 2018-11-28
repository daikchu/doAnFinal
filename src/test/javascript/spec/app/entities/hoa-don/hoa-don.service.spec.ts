/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { HoaDonService } from 'app/entities/hoa-don/hoa-don.service';
import { IHoaDon, HoaDon } from 'app/shared/model/hoa-don.model';

describe('Service Tests', () => {
    describe('HoaDon Service', () => {
        let injector: TestBed;
        let service: HoaDonService;
        let httpMock: HttpTestingController;
        let elemDefault: IHoaDon;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(HoaDonService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new HoaDon('ID', 0, 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        ngayLap: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a HoaDon', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 'ID',
                        ngayLap: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        ngayLap: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new HoaDon(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a HoaDon', async () => {
                const returnedFromService = Object.assign(
                    {
                        khachHangId: 1,
                        loaiKH: 'BBBBBB',
                        ngayLap: currentDate.format(DATE_TIME_FORMAT),
                        tenKHVangLai: 'BBBBBB',
                        addrKHVangLai: 'BBBBBB',
                        sdtKHVangLai: 'BBBBBB',
                        trangThaiNhan: 1,
                        trangThaiThanhToan: 1,
                        note: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        ngayLap: currentDate
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

            it('should return a list of HoaDon', async () => {
                const returnedFromService = Object.assign(
                    {
                        khachHangId: 1,
                        loaiKH: 'BBBBBB',
                        ngayLap: currentDate.format(DATE_TIME_FORMAT),
                        tenKHVangLai: 'BBBBBB',
                        addrKHVangLai: 'BBBBBB',
                        sdtKHVangLai: 'BBBBBB',
                        trangThaiNhan: 1,
                        trangThaiThanhToan: 1,
                        note: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        ngayLap: currentDate
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

            it('should delete a HoaDon', async () => {
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
