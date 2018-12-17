import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { IUsers } from 'app/shared/model/users.model';
import { UsersService } from './users.service';

@Component({
    selector: 'jhi-users-update',
    templateUrl: './users-update.component.html'
})
export class UsersUpdateComponent implements OnInit {
    users: IUsers;
    isSaving: boolean;
    startDateDp: any;
    ngaySinhDp: any;
    dateCreatedDp: any;
    dateUpdatedDp: any;

    constructor(private usersService: UsersService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ users }) => {
            this.users = users;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.users.id !== undefined) {
            this.subscribeToSaveResponse(this.usersService.update(this.users));
        } else {
            this.subscribeToSaveResponse(this.usersService.create(this.users));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUsers>>) {
        result.subscribe((res: HttpResponse<IUsers>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
