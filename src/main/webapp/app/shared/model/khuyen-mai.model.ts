import { Moment } from 'moment';

export interface IKhuyenMai {
    id?: string;
    tieuDe?: string;
    noiDung?: string;
    loaiApDung?: string;
    timeStart?: Moment;
    timeEnd?: Moment;
    mucKM?: number;
    status?: number;
}

export class KhuyenMai implements IKhuyenMai {
    constructor(
        public id?: string,
        public tieuDe?: string,
        public noiDung?: string,
        public loaiApDung?: string,
        public timeStart?: Moment,
        public timeEnd?: Moment,
        public mucKM?: number,
        public status?: number
    ) {}
}
