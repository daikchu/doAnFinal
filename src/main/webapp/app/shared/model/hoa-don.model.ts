import { Moment } from 'moment';

export interface IHoaDon {
    id?: string;
    khachHangId?: number;
    loaiKH?: string;
    ngayLap?: Moment;
    tenKHVangLai?: string;
    addrKHVangLai?: string;
    sdtKHVangLai?: string;
    trangThaiNhan?: number;
    trangThaiThanhToan?: number;
    note?: string;
}

export class HoaDon implements IHoaDon {
    constructor(
        public id?: string,
        public khachHangId?: number,
        public loaiKH?: string,
        public ngayLap?: Moment,
        public tenKHVangLai?: string,
        public addrKHVangLai?: string,
        public sdtKHVangLai?: string,
        public trangThaiNhan?: number,
        public trangThaiThanhToan?: number,
        public note?: string
    ) {}
}
