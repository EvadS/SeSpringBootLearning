# SeSpringBootLearning

Several Boot skeletons samples.
## Git recomendations 

Git commit text rules 

1. Отделяйте заголовок от тела пустой строкой
2. Ограничивайте заголовок 50 символами
3. Пишите заголовок с заглавной буквы
4. Не ставьте точку в конце заголовка
5. Используйте повелительное наклонение в заголовке
6. Переходите на следующую строку в теле на 72 символах
7. В теле отвечайте на вопросы что и почему, а не как

<b>Коммит</b> — это по сути <i>информация об изменениях</i>. Как-то правильнее, с моей точки зрения, ощущается вопрос 
 * <b>«Что эти изменения делают»</b>
 * <b>«Что сделают» </b> а не «Что эти изменения сделали». Т.к. это же не фиксированная точка в истории, коммиты можно переставлять местами, сливать, переносить в другие ветки и т.д.

### Уточнения 
#### Заголовок и тело 
Разбиваем коммит пробелом на заголовок и тело. 

``` 
git commit -am 'commit title

commit body message'
```
Выводим только строку заголовка
``` 
 git log --oneline 
```
группируем коммиты по автору, опять же, для краткости показывает только заголовок
```
 git shortlog 
```

#### Поиск по коммитам 
```
git log --oneline -5 --author 'Evad' --before "Fri Mar 2 2020"
```

#### Заголовок

Правильно составленный заголовок коммита должен завершать следующее предложение

---
If applied, this commit will <заголовок коммита>
---

Например:
* If applied, this commit will <b><i> refactor subsystem X for readability</i></b>
* If applied, this commit will <b><i> update getting started documentation</i></b>
* If applied, this commit will <b><i> remove deprecated methods</i></b>
* If applied, this commit will <b><i> release version 1.0.0</i></b>

