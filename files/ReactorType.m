classdef ReactorType
    methods (Static)
        function params = MKER
            params.class = 'MKER';
            params.burnup = 30;
            params.kpd = 0.352;
            params.enrichment = 0.024;
            params.termal_capacity = 4250;
            params.electrical_capacity = 1500;
            params.life_time = 50;
            params.first_load = 192;
        end
        function params = RBMK
            params.class = 'RBMK';
            params.burnup = 25;
            params.kpd = 0.3125;
            params.enrichment = 0.027;
            params.termal_capacity = 3200;
            params.electrical_capacity = 1000;
            params.life_time = 40;
            params.first_load = 192;
        end
        function params = VVER_1000
            params.class = 'VVER-1200';
            params.burnup = 40;
            params.kpd = 0.33;
            params.enrichment = 0.0385;
            params.termal_capacity = 3200;
            params.electrical_capacity = 1056;
            params.life_time = 60;
            params.first_load = 79.84;
        end
        function params = AP1000
            params.class = 'AP1000';
            params.burnup = 43.20;
            params.kpd = 0.339;
            params.enrichment = 0.048;
            params.termal_capacity = 3415;
            params.electrical_capacity = 1158;
            params.life_time = 60;
            params.first_load = 93.65;
        end
        function params = BWR
            params.class = 'BWR';
            params.burnup = 45;
            params.kpd = 0.343;
            params.enrichment = 0.041;
            params.termal_capacity = 3572;
            params.electrical_capacity = 1252;
            params.life_time = 60;
            params.first_load = 128.00;
        end
        function params = ABWR
            params.class = 'ABWR';
            params.burnup = 46.40;
            params.kpd = 0.3655;
            params.enrichment = 0.036;
            params.termal_capacity = 4500;
            params.electrical_capacity = 1645;
            params.life_time = 60;
            params.first_load = 128.00;
        end
        function params = EPR
            params.class = 'EPR';
            params.burnup = 36.10;
            params.kpd = 0.3515;
            params.enrichment = 0.032;
            params.termal_capacity = 3992;
            params.electrical_capacity = 1404.5;
            params.life_time = 60;
            params.first_load = 140.66;
        end
        function params = MAGNOX
            params.class = 'MAGNOX';
            params.burnup = 4.5;
            params.kpd = 0.23;
            params.enrichment = 0.01;
            params.termal_capacity = 728;
            params.electrical_capacity = 184;
            params.life_time = 60;
            params.first_load = 120;
        end
        function params = BN
            params.class = 'BN';
            params.burnup = 140;
            params.kpd = 42.05;
            params.enrichment = 0.21;
            params.termal_capacity = 2800;
            params.electrical_capacity = 1220;
            params.life_time = 60;
            params.first_load = 50.4;
        end
        
        function params = PWR
            params.class = 'PWR';
            params.burnup = 42.0;
            params.kpd = 34.0;
            params.enrichment = 0.045;
            params.termal_capacity = 3411;
            params.electrical_capacity = 1150;
            params.life_time = 50;
            params.first_load = 74.0;
        end

        function params = CPR_1000
            params.class = 'CPR-1000';
            params.burnup = 52.0;
            params.kpd = 35.1;
            params.enrichment = 0.045;
            params.termal_capacity = 2900;
            params.electrical_capacity = 1021;
            params.life_time = 40;
            params.first_load = 77.0;
        end
        function params = CANDU
            params.class = 'CANDU';
            params.burnup = 8;
            params.kpd = 0.30;
            params.enrichment = 0.0071;
            params.termal_capacity = 2343;
            params.electrical_capacity = 742;
            params.life_time = 60;
            params.first_load = 38.9;
        end
        function params = PHWR
            params.class = 'PHWR';
            params.burnup = 8;
            params.kpd = 0.30;
            params.enrichment = 0.0071;
            params.termal_capacity = 2343;
            params.electrical_capacity = 742;
            params.life_time = 60;
            params.first_load = 38.9;
        end
        function params = KLT_40
            params.class = 'KLT-40';
            params.burnup = 42;
            params.kpd = 0.30;
            params.enrichment = 0.16;
            params.termal_capacity = 150;
            params.electrical_capacity = 32;
            params.life_time = 36;
            params.first_load = 0.007;
        end
        function params = PWR_TEST
            params.class = 'PWR';
            params.burnup = 40;
            params.kpd = 0.33;
            params.enrichment = 0.044;
            params.termal_capacity = 3200;
            params.electrical_capacity = 1056;
            params.life_time = 60;
            params.first_load = 79.84;
        end
        function reactor = fill_info(reactor)
            r_fields = fieldnames(reactor);
            if isempty(reactor.class)
                reactor.class = 'Unknown';
            end
            if strcmp(reactor.class,'Unknown')
                prototipe = ReactorType.PWR;
            else
                switch reactor.class
                    case 'CPR-1000'
                        prototipe = ReactorType.CPR_1000;
                    case {'VVER-1000' 'VVER-1200'}
                        prototipe = ReactorType.VVER_1000;
                    case 'KLT-40'
                        prototipe = ReactorType.KLT_40;
                    otherwise
                        try
                        prototipe = ReactorType.(reactor.class);
                        catch err
                            if strcmp(err.identifier,'MATLAB:subscripting:classHasNoPropertyOrMethod')
                                prototipe = ReactorType.PWR;
                            else 
                                throw(err)
                            end
                        end
                end
            end
            p_fields = fieldnames(prototipe);
            needed_fields = setdiff(p_fields,r_fields);
            for f = 1:length(needed_fields)
                reactor.(needed_fields{f}) = prototipe.(needed_fields{f});
            end
            for p = 2:length(p_fields)
                if isnan(reactor.(p_fields{p})) || isinf(reactor.(p_fields{p})) ...
                        || (reactor.(p_fields{p}) == 0)
                    reactor.(p_fields{p}) = prototipe.(p_fields{p});
                end
            end
            if reactor.first_load > reactor.termal_capacity/reactor.burnup*3
                reactor.first_load = reactor.termal_capacity/reactor.burnup*3*0.75;
            end
        end
    end
end
