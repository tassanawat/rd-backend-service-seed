package th.go.rd.rdoffline.controller;

import th.go.rd.rdoffline.entity.TeamEntity;
import th.go.rd.rdoffline.service.TeamsService;
import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Timed
@RestController
@RequestMapping(value = "/football")
@Api(value = "Teams", description = "Football teams operations")
public class TeamController {

    @Autowired
    private TeamsService teamsService;

    @Autowired
    MetricRegistry metrics;

    private com.codahale.metrics.Meter counter;

    @PostConstruct
    public void init() {
        counter = metrics.meter("requests");
    }

    @ApiOperation(value = "List of available teams", response = TeamEntity.class, responseContainer = "List")
    @RequestMapping(value = "/teams", method = RequestMethod.GET, produces = "application/json")
    public String getTeams() throws JsonProcessingException {
        counter.mark();
        List<TeamEntity> teams = teamsService.findAllTeams();
        return new ObjectMapper().writeValueAsString(teams);
    }

    @ApiOperation(value = "Search team by title", response = TeamEntity.class)
    @RequestMapping(value = "/teams/search", method = RequestMethod.GET, produces = "application/json")
    public String searchTeam(@RequestParam String teamTitle) throws JsonProcessingException {
        counter.mark();
        if (StringUtils.isNotBlank(teamTitle)) {
            TeamEntity team = teamsService.findTeamByTitle(teamTitle);
            if (team != null) {
                return new ObjectMapper().writeValueAsString(team);
            } else {
                return "";
            }
        }
        return "";

    }

    @ApiOperation(value = "Search team by ID", response = TeamEntity.class)
    @RequestMapping(value = "/teams/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getTeamById(@PathVariable Integer id) throws JsonProcessingException {
        counter.mark();
        TeamEntity team = teamsService.findTeamById(id);
        return team != null ? new ObjectMapper().writeValueAsString(team) : "";
    }

    @ApiOperation(value = "create team by key", response = TeamEntity.class)
    @RequestMapping(value = "/teams/create/{key}", method = RequestMethod.PUT, produces = "application/json")
    public void createTeam(@PathVariable String key) throws IOException {
        teamsService.create(key);
    }

    @ApiOperation(value = "Version", response = String.class)
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String version() {
        return "1.0";
    }
}
