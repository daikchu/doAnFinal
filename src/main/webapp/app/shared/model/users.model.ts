import { Moment } from 'moment';

export interface IUsers {
    id?: string;
    userName?: string;
    password?: string;
    email?: string;
    phone?: string;
    address?: string;
    fullName?: string;
    startDate?: Moment;
    trangThai?: boolean;
    cmnd?: string;
    ngaySinh?: Moment;
    gioiTinh?: string;
    roles?: string;
    dateCreated?: Moment;
    dateUpdated?: Moment;
    type?: string;
}

export class Users implements IUsers {
    constructor(
        public id?: string,
        public userName?: string,
        public password?: string,
        public email?: string,
        public phone?: string,
        public address?: string,
        public fullName?: string,
        public startDate?: Moment,
        public trangThai?: boolean,
        public cmnd?: string,
        public ngaySinh?: Moment,
        public gioiTinh?: string,
        public roles?: string,
        public dateCreated?: Moment,
        public dateUpdated?: Moment,
        public type?: string
    ) {
        this.trangThai = this.trangThai || false;
    }
}
