## @OneToOne
Рассмотрим описание аннотации на примере, что каждый гражданин может иметь только один паспорт. 
И у каждого паспорта может быть только один владелец.

## Параметры 

   ---
  
   Владелец отношения содержит аннотацию @JoinColumn для указания столбца внешнего ключа,
    а обратная сторона **ДОЧЕРНЯЯ** отношения содержит атрибут **mappedBy**, указывающий, что отношение
     отображается другим объектом.
   
   --
**optional** говорит JPA, является ли значение в этом поле обязательным или нет.

@**JoinColumn** - объявляем связанное поле, 
  **name**  обозначает поле в БД для создания связи.
  
  **mappedBy** объявить сторону, которая не несет ответственности за отношения,
  **cascade**  со стороны владельца 

## Пример 1
владелец  Person, 
Связь в БД между таблицами users и passports осуществляется посредством поля passport_id в таблице users.
cascade - сделать все действия со связанным объектом
то есть, когда удаляется Person из базы, JPA самостоятельно определит наличие у него паспорта и удалит вначале паспорт, потом гражданина.
Passport не несет отвественности 

## Пример 2
Автомобиль - Гос номер 

**Родитель** RegistrationNumber
видно по аннотации   @**JoinColumn**  и здесь же указываем поле на foreign key (name="auto_id")
CascadeType идет по умолчанию 
для **дочерней** сущности 
указываем 
  *  @OneToOne  один - ко одному связть
  *  optional=false - значение не обязательно 
  *  mappedBy="auto"  не несет ответственности за отношения, ссылаемся на имя свойства связи (auto) на стороне владельца.

Task

@MapsId в указанном поле @OneToOne вместо аннотации @Id, которая будет указывать имя поля первичного ключа целевой сущности. ,