echo Running Classifier
setlocal enabledelayedexpansion
for /r C:\Users\Arthur\Desktop\Mining\Projeto\tweets %%f in (*.*) do (
    for /f "delims=" %%a in ('python classificador.py -c "naive_bayes" -f %%f') do @set myresult=%%a
    IF "!myresult!" == "Is relevant? [1]" (
	MOVE %%f C:\Users\Arthur\Desktop\Mining\Projeto\tweets\hasopinion
    )
)
endlocal