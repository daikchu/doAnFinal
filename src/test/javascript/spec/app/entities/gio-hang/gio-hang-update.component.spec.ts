/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { GioHangUpdateComponent } from 'app/entities/gio-hang/gio-hang-update.component';
import { GioHangService } from 'app/entities/gio-hang/gio-hang.service';
import { GioHang } from 'app/shared/model/gio-hang.model';

describe('Component Tests', () => {
    describe('GioHang Management Update Component', () => {
        let comp: GioHangUpdateComponent;
        let fixture: ComponentFixture<GioHangUpdateComponent>;
        let service: GioHangService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [GioHangUpdateComponent]
            })
                .overrideTemplate(GioHangUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GioHangUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GioHangService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new GioHang('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.gioHang = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new GioHang();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.gioHang = entity;
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
