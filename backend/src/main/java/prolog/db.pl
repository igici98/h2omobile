:- dynamic parameter/4.
:- dynamic iteration/1.

prolog_runs(yes).

parameter(dummy, 20 , 30, 1).
iteration(0).

check_if_iced(PName, Messageice) :-
	parameter(PName, Wert),
	Wert < 0,
	Messageice = 'Das System hat eine Abweichung festgestellt, der Wert des Temperaturparameters liegt im Minusbereich. Der Sensor des Geraetes ist vereist und kann fuer eine korrekte Messung nicht verwendet werden! Bitte enttauen Sie Ihren Sensor oder wechseln Sie das Geraet und wiederholen Sie die Messung! | The system has detected a deviation, the value of the temperature parameter is in the minus range. The sensor of the device is iced and cannot be used for a correct measurement! Please defrost your sensor or change the device and repeat the measurement!'.

check_if_deviation(PName, Message) :-
	parameter(PName, Wert, Obergrenze, Untergrenze),
	(Wert =< Untergrenze; Wert >= Obergrenze),
	Message = 'Das System hat eine Abweichung festgestellt, fuer folgenden Parameter: | The system has detected a deviation for parameter:'.
	
check_if_first_iteration(Iteration, Iterationmessage) :-
	iteration(Iteration),
	Iteration = 1,
	Iterationmessage = 'Das System hat eine Abweichung festgestellt, bitte ueberpruefen Sie, ob die Probenahme korrekt durchgefuehrt wurde und wiederholen Sie ggf. die Messung! | The system has detected a deviation, please check if the sampling was performed correctly and if necessary repeat the sampling!'.

check_if_second_iteration(Iteration, Iterationmessagetwo) :-
	iteration(Iteration),
	Iteration = 2,
	Iterationmessagetwo = 'Das System hat erneut eine Abweichung festgestellt. Bitte wiederholen Sie die Probenahme nach Moeglichkeit an einem anderen Ort! (z.B. stromaufwaerts) | The system has detected a deviation again, when possible please repeat the sampling at a different location! (e.g. upstream)'.
	
check_if_third_iteration(Iteration, Iterationmessagethree) :-
	iteration(Iteration),
	Iteration >= 3,
	Iterationmessagethree = 'Das System hat eine Abweichung festgestellt! Wenn Sie absolut sicher sind, dass bei der Probenahme keine Fehler aufgetreten sind und Sie an der richtigen Stelle sind, beobachten Sie bitte die Stelle auf weitere Besonderheiten und halten Sie Ihre Beobachtung in Ihrem Bericht fest! | The system has detected a deviation! If you are absolutely sure that no errors occurred during the sampling process and that you are at the right place, please observe the place for further peculiarities and record your observation in your report!'.
	
check_if_great_deviation(PName, Messagegreat) :-
	parameter(PName, Wert, Deviationover, Deviationunder),
	(Wert < Deviationunder; Wert >  Deviationover),
	Messagegreat = 'Das System hat bei einem oder mehreren Parameterwerten eine sehr grosse Abweichung festgestellt. Die Grenzwerte wurden deutlich uebertroffen! Anscheinend verwenden Sie ein defektes Geraet. Bitte ueberpruefen Sie Ihren Sensor oder tauschen Sie das Messgeraet aus. Danach wiederholen Sie bitte die Messung! | The system has detected a very high deviation, for one or more parameter values. The barrier values were surpassed significantly! Apparently you are using a defective device. Please check your sensor or change the measuring device. After that please repeat the measurement!'.