/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { KhuyenMaiUpdateComponent } from 'app/entities/khuyen-mai/khuyen-mai-update.component';
import { KhuyenMaiService } from 'app/entities/khuyen-mai/khuyen-mai.service';
import { KhuyenMai } from 'app/shared/model/khuyen-mai.model';

describe('Component Tests', () => {
    describe('KhuyenMai Management Update Component', () => {
        let comp: KhuyenMaiUpdateComponent;
        let fixture: ComponentFixture<KhuyenMaiUpdateComponent>;
        let service: KhuyenMaiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [KhuyenMaiUpdateComponent]
            })
                .overrideTemplate(KhuyenMaiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KhuyenMaiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhuyenMaiService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new KhuyenMai('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.khuyenMai = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new KhuyenMai();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.khuyenMai = entity;
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
