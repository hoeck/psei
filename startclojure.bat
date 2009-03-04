
echo "nur zum spass, das ist nicht teil der semesterarbeit (!!!)"
echo "eine clojure implementation der aufgabe"
echo "im prompt eingeben: (pwd) .. zeigt aktuelles verzeichnis an"
echo "oder: (convert-file "inputfile" "outputfile") um eine datei zu konvertieren"
echo "beenden mit str-c oder (System/exit 0)"

java -jar clojure.jar -e "(use 'hoeck.marchingcube) (in-ns 'hoeck.marchingcube) (doc convert-file) (doc pwd)" -r
