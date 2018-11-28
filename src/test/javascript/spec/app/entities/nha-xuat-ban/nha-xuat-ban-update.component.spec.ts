/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { NhaXuatBanUpdateComponent } from 'app/entities/nha-xuat-ban/nha-xuat-ban-update.component';
import { NhaXuatBanService } from 'app/entities/nha-xuat-ban/nha-xuat-ban.service';
import { NhaXuatBan } from 'app/shared/model/nha-xuat-ban.model';

describe('Component Tests', () => {
    describe('NhaXuatBan Management Update Component', () => {
        let comp: NhaXuatBanUpdateComponent;
        let fixture: ComponentFixture<NhaXuatBanUpdateComponent>;
        let service: NhaXuatBanService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [NhaXuatBanUpdateComponent]
            })
                .overrideTemplate(NhaXuatBanUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NhaXuatBanUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NhaXuatBanService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new NhaXuatBan('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.nhaXuatBan = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new NhaXuatBan();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.nhaXuatBan = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
