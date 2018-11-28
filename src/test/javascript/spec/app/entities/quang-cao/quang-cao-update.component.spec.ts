/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { QuangCaoUpdateComponent } from 'app/entities/quang-cao/quang-cao-update.component';
import { QuangCaoService } from 'app/entities/quang-cao/quang-cao.service';
import { QuangCao } from 'app/shared/model/quang-cao.model';

describe('Component Tests', () => {
    describe('QuangCao Management Update Component', () => {
        let comp: QuangCaoUpdateComponent;
        let fixture: ComponentFixture<QuangCaoUpdateComponent>;
        let service: QuangCaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [QuangCaoUpdateComponent]
            })
                .overrideTemplate(QuangCaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(QuangCaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuangCaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new QuangCao('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.quangCao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new QuangCao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.quangCao = entity;
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
