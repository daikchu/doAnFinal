/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { HoaDonUpdateComponent } from 'app/entities/hoa-don/hoa-don-update.component';
import { HoaDonService } from 'app/entities/hoa-don/hoa-don.service';
import { HoaDon } from 'app/shared/model/hoa-don.model';

describe('Component Tests', () => {
    describe('HoaDon Management Update Component', () => {
        let comp: HoaDonUpdateComponent;
        let fixture: ComponentFixture<HoaDonUpdateComponent>;
        let service: HoaDonService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [HoaDonUpdateComponent]
            })
                .overrideTemplate(HoaDonUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HoaDonUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HoaDonService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new HoaDon('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.hoaDon = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new HoaDon();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.hoaDon = entity;
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
