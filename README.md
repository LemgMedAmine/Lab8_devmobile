<img width="402" height="857" alt="Capture d&#39;écran 2026-05-05 015009" src="https://github.com/user-attachments/assets/3c7e23e8-f44f-4c39-981b-6f2a4c233398" />

Cette capture montre le résultat après l’exécution du bouton « Charger image (Thread) ».
L’application lance un Thread secondaire afin de charger l’image sans bloquer l’interface principale. Une fois le chargement terminé, l’image est affichée au centre de l’écran et le texte de statut indique que l’image a bien été chargée.

<img width="386" height="847" alt="Capture d&#39;écran 2026-05-05 015016" src="https://github.com/user-attachments/assets/071f1645-c3fa-4415-b9e3-b44e3edacd94" />

Cette capture montre le résultat après l’exécution du bouton « Calcul lourd (AsyncTask) ».
L’application effectue un calcul en arrière-plan à l’aide de AsyncTask, puis affiche le résultat obtenu dans la zone de statut :

résultat = 51599823

<img width="386" height="843" alt="Capture d&#39;écran 2026-05-05 015109" src="https://github.com/user-attachments/assets/af199c5c-c259-470f-b25a-c0a1ee875c46" />

Cette capture montre l’affichage d’un Toast après l’appui sur le bouton « Afficher Toast ».
Le message « UI réactive » apparaît temporairement en bas de l’écran, ce qui confirme que l’interface reste réactive même après l’exécution des tâches en arrière-plan.
