# Relatório
- [] O algoritmo deverá acessar as requisições para alocação de memória a partir de um vetor em memória (vetor de requisições), implementado como uma fila circular.
[]Cada requisição deve informar ao algoritmo de alocação de memória o tamanho da variável dinâmica a ser alocada, bem como seu respectivo identificador.
[]Um gerador de requisições randômicas deve alimentar o vetor de requisições para que este sempre disponha de requisições suficientes para possibilitar o trabalho em fluxo contínuo do algoritmo de alocação.
O alocador de memória e o gerador de requisições randômicas devem compor, portanto, duas threads do sistema (uma thread é o alocador de memória e outra thread é o gerador de requisições) e ambos operam de forma paralelo/concorrente sobre o vetor de requisições. Fica a critério de cada equipe optar, para melhora de eficiência e de qualidade de sua solução, por implementar esses dois módulos (alocador e gerador) com mais threads cada um.
O usuário do sistema deve ser capaz de configurar o tamanho da heap1 parafuncionamento do sistema, assim como o intervalo de valores (mínimo e máximo) para geração randômica do tamanho das variáveis a cada requisição produzida pelo gerador.
O algoritmo de alocação deve alocar a memória para cada variável na heap sempre de forma contígua.
O algoritmo de alocação deve monitorar o índice de fragmentação externa do sistema e deve deflagrar a execução de um desalocador de memória para liberar espaço na heap sempre que o índice de fragmentação estiver acima de valor de referência. O algoritmo de desalocação deve selecionar de forma randômica variáveis a serem desalocadas (simulando um fluxo natural de um sistema real com desalocações).
O usuário do sistema deve ser capaz de configurar o índice máximo de fragmentação.
O algoritmo de desalocação deve executar de forma paralela com o alocador (isto é, as alocações devem continuar ocorrendo durante o tempo de reciclagem da memória).
O número de requisições de alocação a serem alocadas na memória deve ser informado pelo usuário do sistema, de modo a calibrar o esforço a ser realizado pelo algoritmo e, por conseguinte, o tempo de execução para todo o sistema.
O usuário também deve informar o limite de ocupação de memória como um percentual, acima do qual o algoritmo de desalocação de memória deve ser executado. Quando o algoritmo de desalocação for executado, ele deve liberar memória até que o percentual de memória livre fique acima de um outro valor de limiar.
O usuário do sistema deve ser capaz de configurar o limite mínimo de RAM até onde o desalocador deve desalocaor requisições.
Fica a critério de cada equipe, implementar um algoritmo de compactação para eliminar a fragmentação externa que se forma a medida que as variáveis vão sendo desalocadas.

Compare o desempenho do gerenciador dinâmico de memória paralelo/concorrente contra o desempenho de uma versão puramente sequencial da aplicação (que tende a ser desenvolvida em um primeiro para validar a lógica de funcionamento do alocador, gerador e desalocador).
Adote para seu sistema configurações que confiram uma carga computacional alta o suficiente para permitir evidenciar diferenças de desempenho entre as versões paralela e sequencial.
Busque variar as configurações do sistema para conhecer e anotar o comportamento em diferentes situações (por exemplo, o que ocorre quando o tamanho médio dos objetos alocados aumenta?).
As características físicas (hardware) e lógicas (software) da máquina usada como plataforma para coleta de dados devem ser apresentadas.
