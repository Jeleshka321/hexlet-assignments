
пройдено 15 уроков из 22

Домашнее задание
Обсуждение
Теория
Сложности и вопросы?

Если вы не знаете, что делать дальше:

Домашние задания основаны на предыдущих уроках, поэтому если пройденного ранее материала недостаточно для решения задачи — это наша вина. Напишите об этом в обсуждениях, мы улучшим описание задачи и расширим теорию, и обязательно вам ответим

Как лучше понять задачу:

Изучите все файлы домашнего задания. Они дадут представление об архитектуре кода и способах его запуска
Запустите тесты. Текст ошибки покажет, что ожидалось и что получилось
Используйте отладочную печать. Она поможет увидеть результаты выполнения кода по шагам

Когда вы застряли, не видите вариантов решения или не можете справиться с ошибкой, почитайте обсуждения и, если останутся вопросы, задайте свой. Мы не будем подсказывать решение, но поможем разобраться с условием задания или выводом тестов. При составлении вопроса опирайтесь на наш гайд «Как правильно задавать вопросы»

@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Все посты</h1>

            <table class="table table-striped">
                @for(var post : page.getPosts())
                    <tr>
                        <td>
                            ${post.getId()}
                        </td>
                        <td>
                            <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                        </td>
                        <td>
                            ${post.getBody()}
                        </td>
                    </tr>
                @endfor
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="?page=${page.getPage() < 2 ? 1 : page.getPage() - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPage()}">${page.getPage()}</a></li>
                    <li class="page-item">
                        <a class="page-link" href="?page=${page.getPage() + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </main>
    </div>
`
)
